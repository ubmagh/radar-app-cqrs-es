package me.ubmagh.immatriculationservicequeryside.mappers;

import me.ubmagh.commonapi.dtos.ShortVehiculDTO;
import me.ubmagh.immatriculationservicequeryside.entities.Vehicule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    public ShortVehiculDTO toShort(Vehicule vehicule);

}
