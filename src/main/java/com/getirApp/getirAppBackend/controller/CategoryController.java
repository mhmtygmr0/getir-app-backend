package com.getirApp.getirAppBackend.controller;

import com.getirApp.getirAppBackend.core.utils.Result;
import com.getirApp.getirAppBackend.core.utils.ResultData;
import com.getirApp.getirAppBackend.core.utils.ResultHelper;
import com.getirApp.getirAppBackend.dto.request.CategoryRequest;
import com.getirApp.getirAppBackend.dto.response.CategoryResponse;
import com.getirApp.getirAppBackend.entity.Category;
import com.getirApp.getirAppBackend.service.category.CategoryService;
import com.getirApp.getirAppBackend.service.modelMapper.ModelMapperService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapperService modelMapper;

    public CategoryController(CategoryService categoryService, ModelMapperService modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = this.modelMapper.forRequest().map(categoryRequest, Category.class);
        this.categoryService.save(category);
        return ResultHelper.created(this.modelMapper.forResponse().map(category, CategoryResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ResultData<CategoryResponse> get(@PathVariable("id") int id) {
        Category category = this.categoryService.get(id);
        CategoryResponse categoryResponse = modelMapper.mapCategoryWithProducts(category);
        return ResultHelper.success(categoryResponse);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CategoryResponse>> getAll() {
        List<Category> categoryList = this.categoryService.getCategoryList();

        // Category'yi ve ilişkili ürünlerini dönüştürmek için yeni metodu kullanıyoruz
        List<CategoryResponse> categoryResponseList = categoryList.stream()
                .map(modelMapper::mapCategoryWithProducts) // mapCategoryWithProducts kullanımı
                .collect(Collectors.toList());

        return ResultHelper.success(categoryResponseList);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> update(@PathVariable int id, @Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = this.modelMapper.forRequest().map(categoryRequest, Category.class);
        category.setId(id);
        this.categoryService.update(category);
        return ResultHelper.success(this.modelMapper.forResponse().map(category, CategoryResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.categoryService.delete(id);
        return ResultHelper.ok();
    }
}
