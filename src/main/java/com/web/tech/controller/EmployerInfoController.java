package com.web.tech.controller;


import com.web.tech.model.EmployerInfo;
import com.web.tech.model.Post;
import com.web.tech.repository.JobApplicationRepository;
import com.web.tech.repository.PostRepository;
import com.web.tech.service.EmployerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employer")
@CrossOrigin("http://localhost:3000/")
public class EmployerInfoController {

    @Autowired
    private EmployerInfoService employerInfoService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    JobApplicationRepository applicationRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;
    
    @PostMapping
    public ResponseEntity<EmployerInfo> createEmployerInfo(@RequestBody EmployerInfo employerInfo) {
        EmployerInfo createdInfo = employerInfoService.createEmployerInfo(employerInfo);
        return ResponseEntity.ok(createdInfo);
    }

    @GetMapping
    public ResponseEntity<List<EmployerInfo>> getAllEmployerInfo() {
        return ResponseEntity.ok(employerInfoService.getAllEmployerInfo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployerInfo> getEmployerInfoById(@PathVariable Long id) {
    	System.out.println(id);
        EmployerInfo employerInfo = employerInfoService.getEmployerInfoById(id);
        
        System.out.println(employerInfo);

        return ResponseEntity.ok(employerInfo);
    }

    @GetMapping("/jobs/{userId}")
    public List<Post> getEmployerJobs(@PathVariable Long userId) {
        EmployerInfo employer = employerInfoService.getEmployerInfoById(userId); // This gets by userId
        if (employer != null) {
            return postRepository.findByEmployerInfo(employer);
        }
        return Collections.emptyList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployerInfo> updateEmployerInfo(
            @PathVariable Long id,
            @RequestBody EmployerInfo updatedInfo) {
        EmployerInfo employerInfo = employerInfoService.updateEmployerInfo(id, updatedInfo);
        System.out.println(id);
        System.out.println(updatedInfo);
        if (employerInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employerInfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployerInfo(@PathVariable Long id) {
        boolean isDeleted = employerInfoService.deleteEmployerInfo(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
//    ------------------------- img ------------
    
    //Job applied call api
    
    @PostMapping("/view")
    public ResponseEntity<Map<String, String>> viewJobSeeker(@RequestBody Map<String, Integer> requestData) {
        long jobseekerId = requestData.get("jid");
        long postId = requestData.get("pid");

        // Business logic for viewing job seeker details
        System.out.println("Viewing Job Seeker: " + jobseekerId + " for Post: " + postId);
        
        applicationRepository.updateJobApplicationStatus("View",postId , jobseekerId);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Job seeker details viewed successfully");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/shortlist")
    public ResponseEntity<Map<String, String>> shortlistJobSeeker(@RequestBody Map<String, Integer> requestData) {
        long jobseekerId = requestData.get("jid");
        long postId = requestData.get("pid");

        applicationRepository.updateJobApplicationStatus("Shortlist",postId , jobseekerId);
        
        // Business logic for shortlisting a job seeker
        System.out.println("Shortlisting Job Seeker: " + jobseekerId + " for Post: " + postId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Job seeker shortlisted successfully");

        return ResponseEntity.ok(response);
    }
    
    
    @Autowired
    private ImageHelper imfs;
    //--------------------------------------------------------
    
    @PostMapping("/updatePhoto/{id}")
    public String updateProfilePhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
    	System.out.println("photo id"+id);
        imfs.isSaveImage(file);
        
       employerInfoService.updateprofile(file.getOriginalFilename(), id);        
        return "Upload Succesfully......";
    }
    
    
    @GetMapping("/imgs/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable int id) {
        try {
      System.out.println(id);
      EmployerInfo employerInfo =employerInfoService.getInfo(id);
            Path imagePath = Paths.get(uploadDir).resolve(employerInfo.getProfilePicture());
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

    
    
}
