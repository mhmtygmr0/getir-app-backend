package com.getirApp.getirAppBackend.service.category;

import com.getirApp.getirAppBackend.core.exception.NotFoundException;
import com.getirApp.getirAppBackend.entity.Category;
import com.getirApp.getirAppBackend.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category get(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kategori bulunamadı: " + id));
    }

    @Override
    public List<Category> getCategoryList() {
        return categoryRepository.findAllByOrderByIdAsc();
    }

    @Transactional
    @Override
    public Category update(Category category) {
        this.get(category.getId());
        return this.categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Category category = this.get(id);
        this.categoryRepository.delete(category);
    }
}
