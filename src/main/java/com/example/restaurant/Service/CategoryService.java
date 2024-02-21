package com.example.restaurant.Service;

import com.example.restaurant.Model.Category;
import com.example.restaurant.Model.City;
import com.example.restaurant.Repository.CategoryRepository;
import com.example.restaurant.Repository.CityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Optional<Category> getcategorybyid(Integer id){return categoryRepository.findById(id);}
    public List<Category> categoryPage(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Category> pagedResult = categoryRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Category>();
        }
    }
    public Page<Category> getCategory(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        return categoryRepository.findAll(pageable);
    }

    public void saveCategory(Category c) {
        categoryRepository.save(c);
    }

    public void deletecategorybyid(Integer i) {
        categoryRepository.deleteCategoryById(i);
    }

    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

}
