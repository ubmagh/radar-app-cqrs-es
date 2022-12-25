package me.ubmagh.radarservicequeryside.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ubmagh.commonapi.events.RadarCreatedEvent;
import me.ubmagh.commonapi.events.RadarDeletedEvent;
import me.ubmagh.commonapi.events.RadarUpdatedEvent;
import me.ubmagh.radarservicequeryside.entities.Radar;
import me.ubmagh.radarservicequeryside.repositories.RadarRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class RadarEventHandlerService {

    private RadarRepository repository;


    @EventHandler
    public void on(RadarCreatedEvent event){
        Radar radar = new Radar(
                event.getId(),
                event.getLatitude(),
                event.getLongitude(),
                event.getVitesse_max()
        );
        repository.save(radar);
        log.info(" 👉👉 Radar persisted  ");
    }

    @EventHandler
    public void on( RadarUpdatedEvent event){
        Radar radar = repository.findById(event.getId()).get();
        radar.setLatitude(event.getLatitude());
        radar.setLongitude(event.getLongitude());
        radar.setVitesse_max(event.getVitesse_max());
        repository.save(radar);
        log.info(" 👉👉 Radar updated ");
    }


    @EventHandler
    public void on( RadarDeletedEvent event){
        repository.deleteById(event.getId());
        log.info(" 👉👉 Radar deleted ! ");
    }

}
