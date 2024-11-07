package com.mpenyana.superheroes.antiHero.service;

import com.mpenyana.superheroes.antiHero.entity.AntiHeroEntity;
import com.mpenyana.superheroes.antiHero.repository.AntiHeroRepository;
import com.mpenyana.superheroes.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AntiHeroService {
    private final AntiHeroRepository antiHeroRepository;

    public AntiHeroService(AntiHeroRepository antiHeroRepository) {
        this.antiHeroRepository = antiHeroRepository;
    }

    public AntiHeroEntity findOrThrow(final UUID id) {
        return antiHeroRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "Anti-hero by id " + id + " was not found"));
    }

    public Iterable<AntiHeroEntity> getAllAntiHeroes() {
        return antiHeroRepository.findAll();
    }

    public AntiHeroEntity getAntiHeroById(UUID id) {
        return findOrThrow(id);
    }

    public AntiHeroEntity addAntiHero(AntiHeroEntity antiHeroEntity) {
        return antiHeroRepository.save(antiHeroEntity);
    }

    public void updateAntiHero(UUID id, AntiHeroEntity antiHeroEntity) {
        findOrThrow(id);
        antiHeroRepository.save(antiHeroEntity);
    }

    public void deleteAntiHero(UUID id) {
        findOrThrow(id);
    }
}

