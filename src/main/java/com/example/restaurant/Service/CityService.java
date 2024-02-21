package com.example.restaurant.Service;

import com.example.restaurant.Model.City;
import com.example.restaurant.Model.User;
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
public class CityService {
    @Autowired
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    public Optional<City> getcitybyid(Integer id){return cityRepository.findById(id);}
    public List<City> citiesPage(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<City> pagedResult = cityRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<City>();
        }
    }
    public Page<City> getCities(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        return cityRepository.findAll(pageable);
    }

    public void saveUser(City c) {
        cityRepository.save(c);
    }

    public void deletecitybyid(Integer i) {
        cityRepository.deleteCityById(i);
    }

    public List<City> getCity() {
        return cityRepository.findAll();
    }
}
