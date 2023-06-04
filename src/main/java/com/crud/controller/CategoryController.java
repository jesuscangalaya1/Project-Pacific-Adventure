package com.crud.controller;

import com.crud.dtos.request.CategoryRequest;
import com.crud.dtos.response.CategoryResponse;
import com.crud.dtos.response.RestResponse;
import com.crud.services.CategoryService;
import com.crud.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<List<CategoryResponse>> listCategories() {
/*
        List<EntityModel<CategoryResponse>> categoryModels = categoryService.listCategories().stream()
                .map(categoryHateoasConfig::toModel).toList();

        CollectionModel<EntityModel<CategoryResponse>> collectionModel = CollectionModel.of(categoryModels)
                .add(linkTo(methodOn(CategoryController.class).listCategories()).withSelfRel());
*/
        return new RestResponse<>(AppConstants.SUCCESS,
                String.valueOf(HttpStatus.OK),
                "CATEGORIES SUCCESSFULLY READED",
                categoryService.listCategories());
    }

    @GetMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return new RestResponse<>(AppConstants.SUCCESS,
                String.valueOf(HttpStatus.OK),
                AppConstants.MESSAGE_ID_CATEGORY + id + " SUCCESSFULLY READED",
                categoryService.getCategoryById(id));
                //categoryHateoasConfig.toModel(categoryService.getCategoryById(id)));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return new RestResponse<>(AppConstants.SUCCESS,
                String.valueOf(HttpStatus.CREATED),
                "CATEGORY SUCCESSFULLY CREATED",
                categoryService.createCategory(categoryRequest));
                //categoryHateoasConfig.toModel(categoryService.createCategory(categoryRequest)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<CategoryResponse> updatedCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        return new RestResponse<>(AppConstants.SUCCESS,
                String.valueOf(HttpStatus.OK),
                AppConstants.MESSAGE_ID_CATEGORY + id + " SUCCESSFULLY UPDATED",
                categoryService.updateCategory(id, categoryRequest));
                //categoryHateoasConfig.toModel(categoryService.updateCategory(id, categoryRequest)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public RestResponse<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new RestResponse<>(AppConstants.SUCCESS,
                String.valueOf(HttpStatus.OK),
                AppConstants.MESSAGE_ID_CATEGORY + id + " SUCCESSFULLY DELETED",
                "null"); // Data null.
    }

}
