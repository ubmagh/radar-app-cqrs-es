package me.ubmagh.radarservicecommandsside.Aggregates;

import lombok.NoArgsConstructor;
import me.ubmagh.commonapi.commands.CreateRadarCommand;
import me.ubmagh.commonapi.commands.DeleteRadarCommand;
import me.ubmagh.commonapi.commands.UpdateRadarCommand;
import me.ubmagh.commonapi.events.RadarCreatedEvent;
import me.ubmagh.commonapi.events.RadarDeletedEvent;
import me.ubmagh.commonapi.events.RadarUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class RadarAggregate {

    @AggregateIdentifier
    private String id;

    private Float latitude;
    private Float longitude;
    private Float vitesse_max;
    private boolean deleted;


    @CommandHandler
    public RadarAggregate( CreateRadarCommand command){
        AggregateLifecycle.apply( new RadarCreatedEvent(
                command.getId(),
                command.getLatitude(),
                command.getLongitude(),
                command.getVitesse_max()
        ));
    }
    @EventSourcingHandler
    public void on(RadarCreatedEvent event){
        this.id=event.getId();
        this.longitude=event.getLongitude();
        this.latitude=event.getLatitude();
        this.vitesse_max = event.getVitesse_max();
        this.deleted=false;
    }


    @CommandHandler
    public void handle( UpdateRadarCommand command){
        if(this.deleted) return;
        AggregateLifecycle.apply( new RadarUpdatedEvent(
                command.getId(),
                command.getLatitude(),
                command.getLongitude(),
                command.getVitesse_max()
        ));
    }
    @EventSourcingHandler
    public void on(RadarUpdatedEvent event){
        this.id=event.getId();
        this.longitude=event.getLongitude();
        this.latitude=event.getLatitude();
        this.vitesse_max = event.getVitesse_max();
        this.deleted=false;
    }


    @CommandHandler
    public void handle( DeleteRadarCommand command){
        if(this.deleted) return;
        AggregateLifecycle.apply( new RadarDeletedEvent(
                command.getId()
        ));
    }
    @EventSourcingHandler
    public void on(RadarDeletedEvent event){
        this.id=event.getId();
        this.deleted=true;
    }

}
