package me.ubmagh.infractionservicequeryside.web;

import lombok.AllArgsConstructor;
import me.ubmagh.commonapi.queries.GetAllInfractionsQuery;
import me.ubmagh.commonapi.queries.GetInfractionById;
import me.ubmagh.infractionservicequeryside.entities.Infraction;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class InfractionQueryController {

    private QueryGateway queryGateway;


    @GetMapping("/infractions")
    public List<Infraction> getInfractionList(){
        List<Infraction> response = queryGateway.query(new GetAllInfractionsQuery(), ResponseTypes.multipleInstancesOf(Infraction.class)).join();
        return response;
    }

    @GetMapping("/infractions/{infrac_id}")
    public Infraction getInfractionById(@PathVariable(name = "infrac_id") String infrac_id){
        Infraction response = queryGateway.query(new GetInfractionById(infrac_id), ResponseTypes.instanceOf(Infraction.class)).join();
        return response;
    }





}
