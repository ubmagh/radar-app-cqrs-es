package me.ubmagh.radarservicequeryside.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ubmagh.commonapi.queries.GetAllRadarsQuery;
import me.ubmagh.commonapi.queries.GetRadarById;
import me.ubmagh.radarservicequeryside.entities.Radar;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class RadarQueryController {

    private QueryGateway queryGateway;


    @GetMapping("/radars")
    public List<Radar> radarsList(){
        List<Radar> response = queryGateway.query(new GetAllRadarsQuery(), ResponseTypes.multipleInstancesOf(Radar.class)).join();
        return response;
    }


    @GetMapping("/radars/{id}")
    public Radar getRadarById(@PathVariable String id){
        Radar response = queryGateway.query(new GetRadarById(id), ResponseTypes.instanceOf(Radar.class)).join();
        return response;
    }

}
