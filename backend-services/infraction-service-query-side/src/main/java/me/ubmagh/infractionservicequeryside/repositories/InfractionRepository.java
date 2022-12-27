package me.ubmagh.infractionservicequeryside.repositories;

import me.ubmagh.infractionservicequeryside.entities.Infraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfractionRepository extends JpaRepository< Infraction, String> {
}
