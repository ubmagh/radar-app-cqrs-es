package me.ubmagh.radarservicecommandsside.web;

import lombok.AllArgsConstructor;
import me.ubmagh.commonapi.commands.CreateRadarCommand;
import me.ubmagh.commonapi.commands.DeleteRadarCommand;
import me.ubmagh.commonapi.commands.UpdateRadarCommand;
import me.ubmagh.commonapi.dtos.CreateRadarRequestDTO;
import me.ubmagh.commonapi.dtos.UpdateRadarRequestDTO;
import me.ubmagh.commonapi.exceptions.InvalidLatitudeException;
import me.ubmagh.commonapi.exceptions.InvalidLongitudeException;
import me.ubmagh.commonapi.exceptions.NegativeSpeedException;
import me.ubmagh.commonapi.exceptions.RadarValidationException;
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
public class RadarController {

    private CommandGateway commandGateway;
    private EventStore eventStore;


    @PostMapping("/create")
    public ResponseEntity<String> createRadar(@RequestBody CreateRadarRequestDTO radar) throws NegativeSpeedException, InvalidLatitudeException, InvalidLongitudeException {

        if( radar.getVitesse_max()<0 )
            throw new NegativeSpeedException("Impossible que la vitesse maximale soit négative !", radar.getVitesse_max());
        if( radar.getLatitude()<-90 || radar.getLatitude()>90)
            throw new InvalidLatitudeException("Latitude entre 90° et -90°", radar.getLatitude());
        if( radar.getLongitude()<-180 || radar.getLongitude()>180)
            throw new InvalidLongitudeException("Longitude entre 180° et -180°", radar.getLongitude());

        CompletableFuture<String> commandResponse = commandGateway.send(new CreateRadarCommand(
                UUID.randomUUID().toString(),
                radar.getLatitude(),
                radar.getLongitude(),
                radar.getVitesse_max()
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
    public ResponseEntity<String> updateRadar( @PathVariable String id, @RequestBody UpdateRadarRequestDTO radar) throws NegativeSpeedException, InvalidLatitudeException, InvalidLongitudeException  {

        if( radar.getVitesse_max()<0 )
            throw new NegativeSpeedException("Impossible que la vitesse maximale soit négative !", radar.getVitesse_max());
        if( radar.getLatitude()<-90 || radar.getLatitude()>90)
            throw new InvalidLatitudeException("Latitude entre 90° et -90°", radar.getLatitude());
        if( radar.getLongitude()<-180 || radar.getLongitude()>180)
            throw new InvalidLongitudeException("Longitude entre 180° et -180°", radar.getLongitude());

        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateRadarCommand(
                id,
                radar.getLatitude(),
                radar.getLongitude(),
                radar.getVitesse_max()
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
        CompletableFuture<String> commandResponse = commandGateway.send(new DeleteRadarCommand(
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


    @ExceptionHandler(RadarValidationException.class)
    public ResponseEntity<String> exceptionHandler(RadarValidationException exception){
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
