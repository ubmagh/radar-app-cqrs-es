package me.ubmagh.infractionservicequeryside.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ubmagh.commonapi.events.InfractionCreatedEvent;
import me.ubmagh.commonapi.events.InfractionDeletedEvent;
import me.ubmagh.commonapi.events.InfractionUpdatedEvent;
import me.ubmagh.commonapi.queries.GetAllInfractionsQuery;
import me.ubmagh.commonapi.queries.GetAllVehiculesQuery;
import me.ubmagh.commonapi.queries.GetInfractionById;
import me.ubmagh.infractionservicequeryside.entities.Infraction;
import me.ubmagh.infractionservicequeryside.repositories.InfractionRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InfractionService {


    private InfractionRepository infractionRepository;

    @EventHandler
    public void on(InfractionCreatedEvent event){// no business logic here !
        Infraction infraction = new Infraction(
                event.getId(),
                event.getMatricule(),
                event.getMaxVitesse(),
                event.getMontant(),
                event.getVitesse(),
                event.getDate()
        );
        infractionRepository.save(infraction);
        log.info(" 👉👉 Infraction created  ");
    }


    @EventHandler
    public void on(InfractionUpdatedEvent event){// no business logic here !
        Infraction infraction = infractionRepository.findById(event.getId()).get();
        infraction.setDate( event.getDate() );
        infraction.setMatricule( event.getMatricule() );
        infraction.setMaxVitesse( event.getMaxVitesse() );
        infraction.setMontant( event.getMontant() );
        infraction.setVitesse( event.getVitesse());
        infractionRepository.save(infraction);
        log.info(" 👉👉 Infraction saved  ");
    }


    @EventHandler
    public void on(InfractionDeletedEvent event){// no business logic here !
        infractionRepository.deleteById(event.getId());
        log.info(" 👉👉 Infraction deleted !");
    }

    @QueryHandler
    public List<Infraction> on(GetAllInfractionsQuery event){// no business logic here !
        return infractionRepository.findAll();
    }

    @QueryHandler
    public Infraction on(GetInfractionById event){// no business logic here !
        return infractionRepository.findById(event.getId()).get();
    }


}
