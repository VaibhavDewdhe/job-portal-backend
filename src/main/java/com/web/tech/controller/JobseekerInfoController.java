package com.web.tech.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.web.tech.model.JobseekerInfo;
import com.web.tech.service.JobseekerInfoService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobseeker")
@CrossOrigin("http://localhost:3000/")
public class JobseekerInfoController {

    private final JobseekerInfoService service;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public JobseekerInfoController(JobseekerInfoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<JobseekerInfo> createJobseeker(@RequestBody JobseekerInfo jobseekerInfo) {
        JobseekerInfo savedJobseeker = service.saveJobseekerInfo(jobseekerInfo);
        return ResponseEntity.ok(savedJobseeker);
    }

    @GetMapping("/{id}")
    public JobseekerInfo getJobseekerById(@PathVariable Long id) {
    	System.out.println("ssssssssss"+id);
       //Optional<JobseekerInfo> jobseekerInfo = service.getJobseekerInfoById(id);
    	JobseekerInfo ll =service.getInfo(id);
    	System.out.println(ll);
        return  ll;  //jobseekerInfo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JobseekerInfo>> getJobseekerByUserId(@PathVariable Long userId) {
        List<JobseekerInfo> jobseekerInfos = service.getJobseekerInfoByUserId(userId);
        return ResponseEntity.ok(jobseekerInfos);
    }

    @GetMapping
    public ResponseEntity<List<JobseekerInfo>> getAllJobseekers() {
        List<JobseekerInfo> jobseekerInfos = service.getAllJobseekers();
        return ResponseEntity.ok(jobseekerInfos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobseeker(@PathVariable Long id) {
        service.deleteJobseekerInfo(id);
        return ResponseEntity.noContent().build();
    }
    //----------------------------
    @PutMapping("/update/{userId}")
    public ResponseEntity<JobseekerInfo> updateJobseekerInfoByUserId(
            @PathVariable Long userId,
            @RequestBody JobseekerInfo updatedJobseekerInfo) {
    	System.err.println(updatedJobseekerInfo.getResume());
        JobseekerInfo updatedInfo = service.updateJobseekerInfoByUserId(userId, updatedJobseekerInfo);
        return ResponseEntity.ok(updatedInfo);
    }
    
    
    @GetMapping("/imgs/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable int id) {
        try {
      System.out.println(id);
      JobseekerInfo jobseekerInfo =service.getInfo(id);
            Path imagePath = Paths.get(uploadDir).resolve(jobseekerInfo.getProfilePicture());
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(imagePath))
                    .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }

    @Autowired
    private ImageHelper imfs;
    //--------------------------------------------------------
    
    @PostMapping("/updatePhoto/{id}")
    public String updateProfilePhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
    	System.out.println("photo id"+id);
        imfs.isSaveImage(file);
        
        service.updateprofile(file.getOriginalFilename(), id);        
        return "Upload Succesfully......";
    }
    
    
}
