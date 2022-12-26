package me.ubmagh.immatriculationservicecommandsside.web;

import lombok.AllArgsConstructor;
import me.ubmagh.commonapi.commands.CreateProprietaireCommand;
import me.ubmagh.commonapi.commands.DeleteProprietaireCommand;
import me.ubmagh.commonapi.commands.UpdateProprietaireCommand;
import me.ubmagh.commonapi.dtos.CreateProprietaireRequestDTO;
import me.ubmagh.commonapi.exceptions.FieldRequiredException;
import me.ubmagh.commonapi.exceptions.InputValidationException;
import me.ubmagh.commonapi.exceptions.InvalidPuissanceValueException;
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
@RequestMapping("/proprietaires")
public class ProprietaireController {

    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public ResponseEntity<String> createProprietaire(@RequestBody CreateProprietaireRequestDTO dto) throws FieldRequiredException {

        if( dto.getEmail().isEmpty() || dto.getNom().isEmpty() || dto.getPrenom().isEmpty() ||  dto.getDate_naissance()==null)
            throw new FieldRequiredException("les champs : nom, prenom, email et date_naissance sont obligatoires ! ");


        CompletableFuture<String> commandResponse = commandGateway.send(new CreateProprietaireCommand(
                UUID.randomUUID().toString(),
                dto.getNom(),
                dto.getPrenom(),
                dto.getEmail(),
                dto.getTel(),
                dto.getDate_naissance()
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
    public ResponseEntity<String> updateRadar(@PathVariable String id, @RequestBody UpdateProprietaireCommand dto) throws FieldRequiredException {

        if( dto.getEmail().isEmpty() || dto.getNom().isEmpty() || dto.getPrenom().isEmpty() ||  dto.getDate_naissance()==null)
            throw new FieldRequiredException("les champs : nom, prenom, email et date_naissance sont obligatoires ! ");


        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateProprietaireCommand(
                id,
                dto.getNom(),
                dto.getPrenom(),
                dto.getEmail(),
                dto.getTel(),
                dto.getDate_naissance()
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
        CompletableFuture<String> commandResponse = commandGateway.send(new DeleteProprietaireCommand(
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
