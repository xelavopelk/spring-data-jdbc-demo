package ru.flamexander.spring.data.jdbc.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.spring.data.jdbc.demo.dtos.CategoryDto;
import ru.flamexander.spring.data.jdbc.demo.dtos.CreateOrUpdateCategoryDtoRq;
import ru.flamexander.spring.data.jdbc.demo.dtos.SimplestPageDto;
import ru.flamexander.spring.data.jdbc.demo.entities.Category;
import ru.flamexander.spring.data.jdbc.demo.exceptions.ResourceNotFoundException;
import ru.flamexander.spring.data.jdbc.demo.services.CategoriesService;

import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    private static final Function<Category, CategoryDto> MAP_TO_DTO_FUNCTION = c -> new CategoryDto(c.getId(), c.getTitle());

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public SimplestPageDto<CategoryDto> findAll() {
        return new SimplestPageDto<>(categoriesService.findAll().stream().map(MAP_TO_DTO_FUNCTION).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return categoriesService.findById(id).map(MAP_TO_DTO_FUNCTION).orElseThrow(() -> new ResourceNotFoundException("Категория не найдена"));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoriesService.deleteById(id);
    }

    @PostMapping
    public void createNewCategory(@RequestBody CreateOrUpdateCategoryDtoRq createOrUpdateCategoryDtoRq) {
        categoriesService.createNewCategory(createOrUpdateCategoryDtoRq);
    }

    @PutMapping
    public void updateCategory(@RequestBody CreateOrUpdateCategoryDtoRq createOrUpdateCategoryDtoRq) {
        categoriesService.fullUpdateCategory(createOrUpdateCategoryDtoRq);
    }
}
