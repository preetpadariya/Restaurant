package com.example.restaurant.Service;

import com.example.restaurant.Model.Area;
import com.example.restaurant.Model.City;
import com.example.restaurant.Repository.AreaRepository;
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
public class AreaService {
    @Autowired
    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }
    public Optional<Area> getareabyid(Integer id){return areaRepository.findById(id);}
    public List<Area> areaPage(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Area> pagedResult = areaRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Area>();
        }
    }
    public Page<Area> getarea(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        return areaRepository.findAll(pageable);
    }

    public void savearea(Area c) {
        areaRepository.save(c);
    }

    public void deleteareabyid(Integer i) {
        areaRepository.deleteAreaById(i);
    }
    public List<Area> getArea() {
        return areaRepository.findAll();
    }

    public List<Area> getAreaByCityId(int id){
        return areaRepository.findByCityId(id);
    }
}
