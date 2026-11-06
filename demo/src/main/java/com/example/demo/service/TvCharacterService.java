package com.example.demo.service;

import com.example.demo.exception.CharacterNotFoundException;
import com.example.demo.model.TvCharacter;
import com.example.demo.repository.TvCharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvCharacterService {

    private final TvCharacterRepository repository;

    public TvCharacterService(TvCharacterRepository repository) {
        this.repository = repository;
    }

    /** Both filters are optional; if both are given, both are applied. */
    public List<TvCharacter> search(String name, String universe) {
        boolean hasName = name != null && !name.isBlank();
        boolean hasUniverse = universe != null && !universe.isBlank();

        if (hasName && hasUniverse) {
            return repository.findByNameContainingIgnoreCaseAndUniverseIgnoreCase(name.trim(), universe.trim());
        }
        if (hasName) {
            return repository.findByNameContainingIgnoreCase(name.trim());
        }
        if (hasUniverse) {
            return repository.findByUniverseIgnoreCase(universe.trim());
        }
        return repository.findAll();
    }

    public TvCharacter getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CharacterNotFoundException(id));
    }

    public TvCharacter create(TvCharacter character) {
        character.setCharacterId(null); // make sure this is always an INSERT
        return repository.save(character);
    }

    public TvCharacter update(Long id, TvCharacter updated) {
        TvCharacter existing = getById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setUniverse(updated.getUniverse());
        existing.setSpecies(updated.getSpecies());
        existing.setRole(updated.getRole());
        existing.setFirstAppearance(updated.getFirstAppearance());
        existing.setImageUrl(updated.getImageUrl());
        return repository.save(existing);
    }

    public void delete(Long id) {
        TvCharacter existing = getById(id);
        repository.delete(existing);
    }

    /** Distinct, sorted universes for the gallery's filter dropdown. */
    public List<String> getUniverses() {
        return repository.findAll().stream()
                .map(TvCharacter::getUniverse)
                .filter(u -> u != null && !u.isBlank())
                .map(String::trim)
                .distinct()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toList();
    }
}