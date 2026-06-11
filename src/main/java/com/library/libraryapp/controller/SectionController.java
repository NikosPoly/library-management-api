package com.library.libraryapp.controller;

import com.library.libraryapp.dto.SectionDTO;
import com.library.libraryapp.model.Section;
import com.library.libraryapp.service.SectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sections")
public class SectionController {

    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    // Create a new Section
    @PostMapping
    public ResponseEntity<Section> createSection( @Valid @RequestBody Section section) {
        Section createdSection = sectionService.createSection(section);
        return ResponseEntity.ok(createdSection);
    }

    // Get Section by ID
    @GetMapping("/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable String id) {
        return sectionService.getSectionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all Sections
    @GetMapping
    public ResponseEntity<List<Section>> getAllSections() {
        return ResponseEntity.ok(sectionService.getAllSections());
    }

    // Update Section
    @PutMapping("/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable String id, @Valid @RequestBody Section updatedSection) {
        Section section = sectionService.updateSection(id, updatedSection);
        return section != null ? ResponseEntity.ok(section) : ResponseEntity.notFound().build();
    }

    /*@PatchMapping("/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        Section updatedSection = sectionService.updateSection(id, updates);
        return updatedSection != null ? ResponseEntity.ok(updatedSection) : ResponseEntity.notFound().build();
    }*/

    @PatchMapping("/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable String id, @RequestBody SectionDTO patchDTO) {
        Section updatedSection = sectionService.updateSection(id, patchDTO);
        return updatedSection != null ? ResponseEntity.ok(updatedSection) : ResponseEntity.notFound().build();
    }



    // Delete Section
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable String id) {
        sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }
}
