package org.example.service.impl;

import org.example.dto.impl.CityDto;
import org.example.exception.ServiceException;
import org.example.mapper.impl.CityMapper;
import org.example.model.impl.City;
import org.example.repository.CityRepository;
import org.example.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@PropertySource("classpath:application.properties")
public class CityService implements EntityService<CityDto> {

    @Autowired
    private CityRepository repository;

    @Autowired
    private CityMapper mapper;

    private static final String notExistingMessage = "К сожалению я не смогу подсказать " +
            "тебе какие достопримечательности данного города можно посетить. Я не бывал в данном городе.";

    public CityService(CityRepository repository, CityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CityDto create(CityDto entity) {
        if(!isExist(entity.getName())){
            return mapper.toDto(repository.save(mapper.fromDto(entity)));
        }
        throw new ServiceException("City with such name already exists in database!");
    }

    @Override
    public CityDto update(CityDto entity) {
        if(!isExist(entity.getName())){
            return mapper.toDto(repository.save(mapper.fromDto(entity)));
        }
        throw new ServiceException("City with such name already exists in database!");
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CityDto getById(Long id) {
        return mapper.toDto(repository.findById(id).get());
    }

    public String getResponseByCityName(String name) {
        City city = repository.findByName(name);
        return Objects.isNull(city) ? notExistingMessage : city.getInfo();
    }

    private boolean isExist(String name){
        return !Objects.isNull(repository.findByName(name));
    }

    @Override
    public List<CityDto> getEntities() {
        List<City> result = new ArrayList<City>();
        repository.findAll().forEach(result::add);
        return (mapper.listToDto(result));
    }
}
