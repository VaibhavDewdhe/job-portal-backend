package com.web.tech.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import com.web.tech.model.EmployeePostDTO;
import com.web.tech.model.EmployerInfo;
import com.web.tech.model.Post;
import com.web.tech.model.User;
import com.web.tech.repository.EmployerInfoRepository;
import com.web.tech.repository.PostRepository;
import com.web.tech.service.EmployerInfoService;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api")
public class PostController {

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	EmployerInfoRepository employerInfoService;
	
    @PostMapping("/post")
    public String createJobPost(@RequestBody Post post, @RequestParam("employerid") Long employerid) {
    	System.out.println("wmp"+employerid);
    EmployerInfo e =employerInfoService.getById(employerid);
    	System.err.println("dssd" +e);
    post.setEmployerInfo(e);
    System.out.println("post"+post);
    postRepository.save(post);
    	
    	return "Sucessfully Add";
    }
    
    @GetMapping("/getallpost")
    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAllPostsWithEmployerInfo();  
        Collections.reverse(posts); // Reverse the list to get LIFO order  
        return posts;
    }
}


