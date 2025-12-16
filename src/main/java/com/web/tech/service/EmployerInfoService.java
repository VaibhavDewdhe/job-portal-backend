package com.web.tech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.tech.model.EmployerInfo;
import com.web.tech.model.JobseekerInfo;
import com.web.tech.repository.EmployerInfoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerInfoService {

    @Autowired
    private EmployerInfoRepository employerInfoRepository;

    public EmployerInfo createEmployerInfo(EmployerInfo employerInfo) {
        return employerInfoRepository.save(employerInfo);
    }

    public List<EmployerInfo> getAllEmployerInfo() {
        return employerInfoRepository.findAll();
    }

    public EmployerInfo getEmployerInfoById(Long id) {
        return employerInfoRepository.findByUserId(id);
    }

    public EmployerInfo updateEmployerInfo(Long id, EmployerInfo updatedInfo) {
        Optional<EmployerInfo> optionalEmployerInfo = employerInfoRepository.findById(id);
        if (optionalEmployerInfo.isPresent()) {
            EmployerInfo existingInfo = optionalEmployerInfo.get();
            existingInfo.setCompanyName(updatedInfo.getCompanyName());
            existingInfo.setIndustry(updatedInfo.getIndustry());
            existingInfo.setLocation(updatedInfo.getLocation());
            existingInfo.setOpenPositions(updatedInfo.getOpenPositions());
            existingInfo.setPortfolioUrl(updatedInfo.getPortfolioUrl());
            return employerInfoRepository.save(existingInfo);
        }
        return null;
    }

    public boolean deleteEmployerInfo(Long id) {
        if (employerInfoRepository.existsById(id)) {
            employerInfoRepository.deleteById(id);
            return true;
        }
        return false;
    }
    //-------------------------------------img---------------------
    
   
    public int updateprofile(String name ,long id)
    {
    return 	employerInfoRepository.updateProfileByUserId(name, id);
    }
    
    
    public EmployerInfo getInfo(long id)
    {
    	return  employerInfoRepository.findFirstByUserId(id);
    }

    
}
