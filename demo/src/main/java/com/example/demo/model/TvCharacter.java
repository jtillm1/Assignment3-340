package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "characters")
public class TvCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characterId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    @Column(length = 2000)
    private String description;

    private String universe;
    private String species;
    private String role;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate firstAppearance;

    // Empty, a web link (http/https), or a local file such as /img/luna.jpg
    @Pattern(regexp = "^(https?://.+|/.+)?$", message = "Must be a web link (https://...) or a path like /img/name.jpg")
    @Column(length = 1000)
    private String imageUrl;

    public TvCharacter() {}

    public Long getCharacterId() { return characterId; }
    public void setCharacterId(Long characterId) { this.characterId = characterId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUniverse() { return universe; }
    public void setUniverse(String universe) { this.universe = universe; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDate getFirstAppearance() { return firstAppearance; }
    public void setFirstAppearance(LocalDate firstAppearance) { this.firstAppearance = firstAppearance; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}