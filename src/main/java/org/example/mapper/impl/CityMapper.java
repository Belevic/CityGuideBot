package org.example.mapper.impl;

import org.example.dto.impl.CityDto;
import org.example.mapper.CustomMapper;
import org.example.model.impl.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityMapper implements CustomMapper<CityDto, City> {

    @Autowired
    private ModelMapper mapper;

    public CityMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public CityDto toDto(City city) {
        return mapper.map(city,CityDto.class);
    }

    @Override
    public City fromDto(CityDto cityDto) {
        return mapper.map(cityDto,City.class);
    }

    @Override
    public List<CityDto> listToDto(List<City> entities) {
        return entities.stream().map(city -> toDto(city)).collect(Collectors.toList());
    }

    @Override
    public List<City> listFromDto(List<CityDto> dtos) {
        return dtos.stream().map(city -> fromDto(city)).collect(Collectors.toList());
    }
}

