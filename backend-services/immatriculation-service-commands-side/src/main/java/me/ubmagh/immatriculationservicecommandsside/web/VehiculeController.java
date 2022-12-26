package me.ubmagh.immatriculationservicecommandsside.web;

import lombok.AllArgsConstructor;
import me.ubmagh.commonapi.commands.*;
import me.ubmagh.commonapi.dtos.CreateVehiculeRequestDTO;
import me.ubmagh.commonapi.exceptions.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicules")
public class VehiculeController {

    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public ResponseEntity<String> createVehicule(@RequestBody CreateVehiculeRequestDTO vehicule) throws FieldRequiredException, InvalidPuissanceValueException {

        if( vehicule.getMatricule()==null || vehicule.getModele()==null || vehicule.getMarque()==null)
            throw new FieldRequiredException("Un champ est manquant! ");
        if( vehicule.getPuissance()<=0 )
            throw new InvalidPuissanceValueException("Impossible que la puissance soit négative ou nulle !", vehicule.getPuissance());


        CompletableFuture<String> commandResponse = commandGateway.send(new CreateVehiculeCommand(
                UUID.randomUUID().toString(),
                vehicule.getMarque(),
                vehicule.getMatricule(),
                vehicule.getModele(),
                vehicule.getPuissance()
        ));
        String response=null;
        try{
            response = commandResponse.get();
        } catch (Exception exc){
            return ResponseEntity.unprocessableEntity().body(response);
        }
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRadar(@PathVariable String id, @RequestBody UpdateVehiculeCommand vehicule) throws FieldRequiredException, InvalidPuissanceValueException {

        if( vehicule.getMatricule()==null || vehicule.getModele()==null || vehicule.getMarque()==null)
            throw new FieldRequiredException("Un champ est manquant! ");
        if( vehicule.getPuissance()<=0 )
            throw new InvalidPuissanceValueException("Impossible que la puissance soit négative ou nulle !", vehicule.getPuissance());

        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateVehiculeCommand(
                id,
                vehicule.getMarque(),
                vehicule.getMatricule(),
                vehicule.getModele(),
                vehicule.getPuissance()
        ));
        String response=null;
        try{
            response = commandResponse.get();
        } catch (Exception exc){
            return ResponseEntity.unprocessableEntity().body(response);
        }
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRadar( @PathVariable String id)  {
        CompletableFuture<String> commandResponse = commandGateway.send(new DeleteVehiculeCommand(
                id
        ));
        String response=null;
        try{
            response = commandResponse.get();
        } catch (Exception exc){
            return ResponseEntity.unprocessableEntity().body(response);
        }
        return ResponseEntity.ok(response);
    }


    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<String> exceptionHandler(InputValidationException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/eventStore/{id}")
    public Stream eventStore(@PathVariable String id ) {
        return eventStore.readEvents( id).asStream();
    }

}
