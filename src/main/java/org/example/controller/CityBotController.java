package org.example.controller;

import org.example.dto.impl.CityDto;
import org.example.service.impl.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/city")
public class CityBotController {

    @Autowired
    private CityService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityDto create(@Valid @RequestBody CityDto cityDto) {
        return service.create(cityDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CityDto update(@PathVariable @Min(1) Long id, @Valid @RequestBody CityDto cityDto) {
        cityDto.setId(id);
        return service.update(cityDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable @Min(1) Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CityDto searchById(@PathVariable @Min(1) Long id) {
        return service.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CityDto> searchAll() {
        return service.getEntities();
    }
}
