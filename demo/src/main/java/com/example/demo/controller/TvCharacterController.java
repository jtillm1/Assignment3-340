package com.example.demo.controller;

import com.example.demo.model.TvCharacter;
import com.example.demo.service.TvCharacterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class TvCharacterController {

    private final TvCharacterService service;

    public TvCharacterController(TvCharacterService service) {
        this.service = service;
    }

    // GET /api/characters, ?name=abc, ?universe=Marvel, or both
    @GetMapping
    public ResponseEntity<List<TvCharacter>> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String universe) {
        return ResponseEntity.ok(service.search(name, universe));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvCharacter> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<TvCharacter> create(@Valid @RequestBody TvCharacter character) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(character));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TvCharacter> update(@PathVariable Long id, @Valid @RequestBody TvCharacter character) {
        return ResponseEntity.ok(service.update(id, character));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
