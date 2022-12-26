package me.ubmagh.immatriculationservicequeryside.repositories;

import me.ubmagh.immatriculationservicequeryside.entities.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietaireRepository extends JpaRepository<Proprietaire, String> {
}
