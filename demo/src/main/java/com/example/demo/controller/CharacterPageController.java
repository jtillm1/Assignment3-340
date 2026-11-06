package com.example.demo.controller;

import com.example.demo.exception.CharacterNotFoundException;
import com.example.demo.model.TvCharacter;
import com.example.demo.service.TvCharacterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

/** Serves the HTML pages. Talks to the service layer, never to the repository. */
@Controller
public class CharacterPageController {

    private final TvCharacterService service;

    public CharacterPageController(TvCharacterService service) {
        this.service = service;
    }

    // ---------- Gallery (Home) ----------
    @GetMapping("/")
    public String gallery(@RequestParam(required = false) String name,
                          @RequestParam(required = false) String universe,
                          Model model) {
        model.addAttribute("characters", service.search(name, universe));
        model.addAttribute("universes", service.getUniverses());
        model.addAttribute("name", name);
        model.addAttribute("universe", universe);
        return "gallery";
    }

    @GetMapping("/characters")
    public String galleryAlias() {
        return "redirect:/";
    }

    // ---------- About ----------
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    // ---------- Details ----------
    @GetMapping("/characters/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("character", service.getById(id));
        return "details";
    }

    // ---------- Add ----------
    @GetMapping("/characters/new")
    public String addForm(Model model) {
        prepareForm(model, new TvCharacter(), "Add a Character", "/characters", null);
        return "form";
    }

    @PostMapping("/characters")
    public String create(@Valid @ModelAttribute("character") TvCharacter character,
                         BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            prepareForm(model, character, "Add a Character", "/characters", result);
            return "form";
        }
        TvCharacter saved = service.create(character);
        redirect.addFlashAttribute("message", saved.getName() + " was added");
        return "redirect:/";   // Post/Redirect/Get: refreshing will not resubmit
    }

    // ---------- Edit ----------
    @GetMapping("/characters/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        prepareForm(model, service.getById(id), "Edit Character", "/characters/" + id + "/edit", null);
        return "form";
    }

    @PostMapping("/characters/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("character") TvCharacter character,
                         BindingResult result, Model model, RedirectAttributes redirect) {
        service.getById(id); // throws -> 404 page if the id does not exist
        if (result.hasErrors()) {
            character.setCharacterId(id);
            prepareForm(model, character, "Edit Character", "/characters/" + id + "/edit", result);
            return "form";
        }
        TvCharacter saved = service.update(id, character);
        redirect.addFlashAttribute("message", saved.getName() + " was updated");
        return "redirect:/characters/" + id;
    }

    // ---------- Delete ----------
    @PostMapping("/characters/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        String name = service.getById(id).getName();
        service.delete(id);
        redirect.addFlashAttribute("message", name + " was removed");
        return "redirect:/";
    }

    // ---------- Not found ----------
    @ExceptionHandler({CharacterNotFoundException.class, MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "not-found";
    }

    // ---------- helpers ----------
    private void prepareForm(Model model, TvCharacter character, String title, String action, BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        if (result != null) {
            for (FieldError fe : result.getFieldErrors()) {
                String msg = "typeMismatch".equals(fe.getCode()) ? "Invalid value" : fe.getDefaultMessage();
                errors.putIfAbsent(fe.getField(), msg);
            }
        }
        model.addAttribute("character", character);
        model.addAttribute("formTitle", title);
        model.addAttribute("formAction", action);
        model.addAttribute("errors", errors);
    }
}
