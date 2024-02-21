package com.example.restaurant.Service;

import com.example.restaurant.Model.Area;
import com.example.restaurant.Model.Category;
import com.example.restaurant.Model.SubCategory;
import com.example.restaurant.Repository.AreaRepository;
import com.example.restaurant.Repository.SubCatRepository;
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
public class SubCatService {
    @Autowired
    private final SubCatRepository subCatRepository;

    public SubCatService(SubCatRepository subCatRepository) {
        this.subCatRepository = subCatRepository;
    }
    public Optional<SubCategory> getsubCatbyid(Integer id){return subCatRepository.findById(id);}
    public List<SubCategory> subCatPage(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<SubCategory> pagedResult = subCatRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<SubCategory>();
        }
    }
    public Page<SubCategory> getSubCategory(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        return subCatRepository.findAll(pageable);
    }
    public void savesubCat(SubCategory c) {
        subCatRepository.save(c);
    }

    public void deletesucatbyid(Integer i) {
        subCatRepository.deleteSubCategoryById(i);
    }
    public List<SubCategory> getSubCategoryByCategoryiD(int id){
        return subCatRepository.findByCategoryId(id);
    }

    public List<SubCategory> getSubCategory() {
        return subCatRepository.findAll();
    }

}
