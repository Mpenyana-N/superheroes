package com.mpenyana.superheroes.antiHero.controller;

import com.mpenyana.superheroes.antiHero.dto.AntiHeroDto;
import com.mpenyana.superheroes.antiHero.entity.AntiHeroEntity;
import com.mpenyana.superheroes.antiHero.service.AntiHeroService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/anti-heroes")
public class AntiHeroController {
    private final AntiHeroService antiHeroService;
    private final ModelMapper modelMapper;

//    Converting a DTO to an Entity
    private AntiHeroEntity convertToEntity(AntiHeroDto antiHeroDto) {
        return modelMapper.map(antiHeroDto, AntiHeroEntity.class);

    }

//    Converting an Entity to a DTO
    private AntiHeroDto convertToDto(AntiHeroEntity antiHeroEntity) {
        return modelMapper.map(antiHeroEntity, AntiHeroDto.class);
    }

    public AntiHeroController(AntiHeroService antiHeroService, ModelMapper modelMapper) {
        this.antiHeroService = antiHeroService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<AntiHeroDto> getAllAntiHeroes() {
        var antiHeroList = StreamSupport.stream(
                antiHeroService.getAllAntiHeroes().spliterator(), false).toList();
        return  antiHeroList.stream().map(this::convertToDto).toList();

    }

    @GetMapping("/{id}")
    public AntiHeroDto getAntiHero(@PathVariable("id") UUID id) {
        return convertToDto(antiHeroService.getAntiHeroById(id));
    }


    @PostMapping
    public AntiHeroDto createAntiHero(@RequestBody AntiHeroDto antiHeroDto) {
        var entity = convertToEntity(antiHeroDto);
        var antiHero = antiHeroService.addAntiHero(entity);
        return convertToDto(antiHero);
    }

    @PutMapping("/{id}")
    public void updateAntiHero(@PathVariable UUID id, @RequestBody AntiHeroDto antiHeroDto ) {
        if (!id.equals(antiHeroDto.getId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match");
        var antiHeroEntity = convertToEntity(antiHeroDto);
        antiHeroService.updateAntiHero(id, antiHeroEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteAntiHero(@PathVariable UUID id) {
        antiHeroService.deleteAntiHero(id);
    }
}
