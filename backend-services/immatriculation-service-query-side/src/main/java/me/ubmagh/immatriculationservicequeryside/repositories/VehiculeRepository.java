package me.ubmagh.immatriculationservicequeryside.repositories;

import me.ubmagh.immatriculationservicequeryside.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, String> {



}
