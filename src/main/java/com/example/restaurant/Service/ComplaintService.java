package com.example.restaurant.Service;

import com.example.restaurant.Model.Complaint;
import com.example.restaurant.Repository.ComplaintRepository;
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
public class ComplaintService {
    @Autowired
    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }
    public Optional<Complaint> getcomplaint(Integer id){return complaintRepository.findById(id);}
    public List<Complaint> areaPage(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Complaint> pagedResult = complaintRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Complaint>();
        }
    }
    public Page<Complaint> getcomplaint(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        return complaintRepository.findAll(pageable);
    }

    public void saveComplaint(Complaint c) {
        complaintRepository.save(c);
    }

    public List<Complaint> getArea() {
        return complaintRepository.findAll();
    }
    public List<Complaint> getAllComplaintsByRestaurant(Integer integer) {
        return complaintRepository.findAllByRestaurantId(integer);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }
}
