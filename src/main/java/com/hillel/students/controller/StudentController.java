package com.hillel.students.controller;

import com.hillel.students.domain.student.Student;
import com.hillel.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/add")
    public String showAddStudent(Student student) {
        return "add-student";
    }

    @GetMapping("/")
    public String getStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String addStudent(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }

        studentRepository.save(student);
        model.addAttribute("students", studentRepository.findAll());
        return "index";

    }


}
