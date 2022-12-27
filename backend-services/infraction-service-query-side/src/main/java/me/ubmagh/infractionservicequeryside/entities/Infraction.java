package me.ubmagh.infractionservicequeryside.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.ubmagh.commonapi.commands.CreateProprietaireCommand;
import me.ubmagh.commonapi.events.ProprietaireCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateLifecycle;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Infraction {

    @Id
    private String id;

    private String matricule;
    private Float maxVitesse;
    private Double montant;
    private Float vitesse;
    private Date date;


}
