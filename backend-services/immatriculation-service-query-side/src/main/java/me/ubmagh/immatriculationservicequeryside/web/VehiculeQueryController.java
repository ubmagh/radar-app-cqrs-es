package me.ubmagh.immatriculationservicequeryside.web;

import lombok.AllArgsConstructor;
import me.ubmagh.commonapi.queries.GetAllVehiculesQuery;
import me.ubmagh.commonapi.queries.GetVehiculeById;
import me.ubmagh.immatriculationservicequeryside.entities.Vehicule;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class VehiculeQueryController {

    private QueryGateway queryGateway;


    @GetMapping("/vehicules/{prop_id}")
    public Vehicule getVehiculeById(@PathVariable String prop_id){
        Vehicule response = queryGateway.query(new GetVehiculeById(prop_id), ResponseTypes.instanceOf(Vehicule.class)).join();
        return response;
    }

    @GetMapping("/vehicules")
    public List<Vehicule> getAllVehiculesList(){
        List<Vehicule> response = queryGateway.query(new GetAllVehiculesQuery(), ResponseTypes.multipleInstancesOf(Vehicule.class)).join();
        return response;
    }




}
