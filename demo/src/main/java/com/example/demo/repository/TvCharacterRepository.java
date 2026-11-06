package com.example.demo.repository;

import com.example.demo.model.TvCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TvCharacterRepository extends JpaRepository<TvCharacter, Long> {

    List<TvCharacter> findByUniverseIgnoreCase(String universe);

    List<TvCharacter> findByNameContainingIgnoreCase(String name);

    List<TvCharacter> findByNameContainingIgnoreCaseAndUniverseIgnoreCase(String name, String universe);
}
