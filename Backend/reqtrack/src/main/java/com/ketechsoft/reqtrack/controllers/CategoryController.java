package com.ketechsoft.reqtrack.controllers;

import com.ketechsoft.reqtrack.constants.Mappings;
import com.ketechsoft.reqtrack.dtos.CategoryDto;
import com.ketechsoft.reqtrack.models.Category;
import com.ketechsoft.reqtrack.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Mappings.CATEGORY_PATH)
@Api(value = "", description = "Category APIs")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    @ApiOperation(value = "Get All Category Operation", response = CategoryDto.class)
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping("/")
    @ApiOperation(value = "Create Operation", response = CategoryDto.class)
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDTO) {
        return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
    }

    @PutMapping("/")
    @ApiOperation(value = "Update Operation", response = CategoryDto.class)
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDTO));
    }

    @GetMapping("/{categoryID}")
    @ApiOperation(value = "Get By Id Operation", response = CategoryDto.class)
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(name = "categoryID") long categoryID) {
        return ResponseEntity.ok(categoryService.getById(categoryID));
    }
}
