package me.ubmagh.immatriculationservicequeryside.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ubmagh.commonapi.dtos.ShortVehiculDTO;
import me.ubmagh.commonapi.queries.*;
import me.ubmagh.immatriculationservicequeryside.entities.Proprietaire;
import me.ubmagh.immatriculationservicequeryside.entities.Vehicule;
import me.ubmagh.immatriculationservicequeryside.mappers.VehiculeMapper;
import me.ubmagh.immatriculationservicequeryside.repositories.ProprietaireRepository;
import me.ubmagh.immatriculationservicequeryside.repositories.VehiculeRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class QueryHandlerService {

    private ProprietaireRepository proprietaireRepository;
    private VehiculeRepository vehiculeRepository;
    private VehiculeMapper vehiculeMapper;


    @QueryHandler
    public List<Proprietaire> on(GetAllProprietairesQuery query){
        return proprietaireRepository.findAll();
    }

    @QueryHandler
    public List<ShortVehiculDTO> on(GetAllVehiculesQuery query){
        List<Vehicule> list = vehiculeRepository.findAll();
        return list.stream().map(v->vehiculeMapper.toShort(v)).collect(Collectors.toList());
    }

    @QueryHandler
    public Vehicule on(GetVehiculeById query){
        return vehiculeRepository.findById(query.getId()).get();
    }

    @QueryHandler
    public Proprietaire on(GetProprietaireById query){
        return proprietaireRepository.findById(query.getId()).get();
    }

}
