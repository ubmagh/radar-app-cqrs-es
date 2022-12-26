package me.ubmagh.immatriculationservicequeryside.web;

import lombok.AllArgsConstructor;
import me.ubmagh.commonapi.queries.GetAllProprietairesQuery;
import me.ubmagh.commonapi.queries.GetProprietaireById;
import me.ubmagh.immatriculationservicequeryside.entities.Proprietaire;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProprietaireQueryController {

    private QueryGateway queryGateway;


    @GetMapping("/proprietaires/{prop_id}")
    public Proprietaire getProprietaireById(@PathVariable String prop_id){
        Proprietaire response = queryGateway.query(new GetProprietaireById(prop_id), ResponseTypes.instanceOf(Proprietaire.class)).join();
        return response;
    }

    @GetMapping("/proprietaires")
    public List<Proprietaire> getAllProprietairesList(){
        List<Proprietaire> response = queryGateway.query(new GetAllProprietairesQuery(), ResponseTypes.multipleInstancesOf(Proprietaire.class)).join();
        return response;
    }




}
