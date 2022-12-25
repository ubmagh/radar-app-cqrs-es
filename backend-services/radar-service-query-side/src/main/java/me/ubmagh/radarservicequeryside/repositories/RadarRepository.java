package me.ubmagh.radarservicequeryside.repositories;

import me.ubmagh.radarservicequeryside.entities.Radar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadarRepository extends JpaRepository<Radar, String> {
}
