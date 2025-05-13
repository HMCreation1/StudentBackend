package com.student.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Student;
import com.student.repository.Repository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Controller {
	
	@Autowired
	private Repository repo ;
	
	@GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
    	List<Student> response=repo.findAll();
        return new ResponseEntity<List<Student>>(response,HttpStatus.OK);
    }
	@PostMapping("/addstudent")
    public ResponseEntity<?> addStudent( @RequestBody Student student) {
    	Student stud=repo.save(student);
    	
        return new ResponseEntity<Student>(stud,HttpStatus.CREATED);
    }
	 
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable int id) {
		Optional<Student> st=repo.findById(id);
		if (st.isPresent()) {
			try {
		    	
		        repo.deleteById(id);
		        Map<String, String> response = new HashMap<>();
		        response.put("message", "Student deleted successfully.");
		        return ResponseEntity.ok(response);
		    } catch (Exception e) {
		    	Map<String, String> response = new HashMap<>();
		        response.put("message", "Failed to delete student. Please try again later.");
		        return ResponseEntity.ok(response);
		    }
		}else {
			Map<String, String> response = new HashMap<>();
			response.put("message", "Student not found.");
	        return ResponseEntity.ok(response);
		}
		
	    
	}


}
