package me.ubmagh.infractionservicecommandsside.Aggregates;

import lombok.NoArgsConstructor;
import me.ubmagh.commonapi.commands.*;
import me.ubmagh.commonapi.events.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Aggregate
@NoArgsConstructor
public class InfractionAggregate {

    @AggregateIdentifier
    private String id;

    private String matricule;
    private Float maxVitesse;
    private Double montant;
    private Float vitesse;
    private Date date;

    private boolean deleted;


    @CommandHandler
    public InfractionAggregate( CreateInfractionCommand command){
        AggregateLifecycle.apply( new InfractionCreatedEvent(
                command.getId(),
                command.getMatricule(),
                command.getMaxVitesse(),
                command.getMontant(),
                command.getVitesse(),
                command.getDate()
        ));
    }
    @EventSourcingHandler
    public void on(InfractionCreatedEvent event){
        this.id=event.getId();
        this.date=event.getDate();
        this.matricule=event.getMatricule();
        this.maxVitesse=event.getMaxVitesse();
        this.vitesse=event.getVitesse();
        this.montant=event.getMontant();
        this.deleted=false;
    }


    @CommandHandler
    public void handle( UpdateInfractionCommand command){
        if(this.deleted) return;
        AggregateLifecycle.apply( new InfractionUpdatedEvent(
                command.getId(),
                command.getMatricule(),
                command.getMaxVitesse(),
                command.getMontant(),
                command.getVitesse(),
                command.getDate()
        ));
    }
    @EventSourcingHandler
    public void on(InfractionUpdatedEvent event){
        this.id=event.getId();
        this.date=event.getDate();
        this.matricule=event.getMatricule();
        this.maxVitesse=event.getMaxVitesse();
        this.vitesse=event.getVitesse();
        this.montant=event.getMontant();
        this.deleted=false;
    }


    @CommandHandler
    public void handle( DeleteInfractionCommand command){
        if(this.deleted) return;
        AggregateLifecycle.apply( new InfractionDeletedEvent(
                command.getId()
        ));
    }
    @EventSourcingHandler
    public void on(InfractionDeletedEvent event){
        this.id=event.getId();
        this.deleted=true;
    }



}
