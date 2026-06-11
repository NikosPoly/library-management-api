package com.library.libraryapp.service;

import com.library.libraryapp.dto.SectionDTO;
import com.library.libraryapp.model.Section;
import com.library.libraryapp.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public Optional<Section> getSectionById(String id) {
        return sectionRepository.findById(id);
    }

    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    // Need to include partial update for fields
    public Section updateSection(String id, Section updatedSection) {
        return sectionRepository.findById(id)
                .map(existingSection -> {
                    existingSection.setName(updatedSection.getName());
                    existingSection.setDescription(updatedSection.getDescription());
                    existingSection.setBookIds(updatedSection.getBookIds());
                    existingSection.setEmployeeIds(updatedSection.getEmployeeIds());
                    return sectionRepository.save(existingSection);
                })
                .orElse(null);
    }

    public Section updateSection(String id, SectionDTO patchDTO) {
        // Validate all provided fields first
        boolean isValid = true;

        if (patchDTO.getName() != null && patchDTO.getName().isBlank()) {
            isValid = false;
        }

        if (patchDTO.getDescription() != null && patchDTO.getDescription().isBlank()) {
            isValid = false;
        }

        if (patchDTO.getBookIds() != null && patchDTO.getBookIds().isEmpty()) {
            isValid = false;
        }

        if (patchDTO.getEmployeeIds() != null && patchDTO.getEmployeeIds().isEmpty()) {
            isValid = false;
        }

        // If any provided field is invalid, reject the update
        if (!isValid) {
            System.out.println("One or more fields in the patch are invalid.");
            return null;
        }


        // Proceed to update
        return sectionRepository.findById(id)
                .map(existingSection -> {
                    if (patchDTO.getName() != null) {
                        existingSection.setName(patchDTO.getName());
                    }

                    if (patchDTO.getDescription() != null) {
                        existingSection.setDescription(patchDTO.getDescription());
                    }

                    if (patchDTO.getBookIds() != null) {
                        existingSection.setBookIds(patchDTO.getBookIds());
                    }

                    if (patchDTO.getEmployeeIds() != null) {
                        existingSection.setEmployeeIds(patchDTO.getEmployeeIds());
                    }

                    return sectionRepository.save(existingSection);
                })
                .orElse(null);
    }




    /*public Section updateSection(String id, Map<String, Object> updates) {
        return sectionRepository.findById(id)
                .map(existingSection -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "name" -> existingSection.setName((String) value);
                            case "description" -> existingSection.setDescription((String) value);
                            case "bookIds" -> existingSection.setBookIds((List<String>) value);
                            case "employeeIds" -> existingSection.setEmployeeIds((List<String>) value);
                            // You can add more fields if needed
                        }
                    });
                    return sectionRepository.save(existingSection);
                })
                .orElse(null);
    }*/


    public void deleteSection(String id) {
        if(sectionRepository.existsById(id)){
            sectionRepository.deleteById(id);
        }else{
            throw new RuntimeException("Section with ID " + id + " not found.");
        }
    }


}
