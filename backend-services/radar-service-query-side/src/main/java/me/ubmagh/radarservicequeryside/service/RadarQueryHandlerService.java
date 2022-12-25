package me.ubmagh.radarservicequeryside.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ubmagh.commonapi.queries.GetAllRadarsQuery;
import me.ubmagh.commonapi.queries.GetRadarById;
import me.ubmagh.radarservicequeryside.entities.Radar;
import me.ubmagh.radarservicequeryside.repositories.RadarRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class RadarQueryHandlerService {

    private RadarRepository repository;


    @QueryHandler
    public List<Radar> on(GetAllRadarsQuery query){
        return repository.findAll();
    }

    @QueryHandler
    public Radar on( GetRadarById query){
        return repository.findById(query.getId()).get();
    }



}
