package dev.controller;

import dev.domain.Student;
import dev.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.SQLException;
import java.util.List;

@Controller

public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){this.studentService=studentService;}

    @GetMapping("/students")
    public String getAllStudents(Model model) throws SQLException {
        List<Student> listStudents = studentService.listAll();
        model.addAttribute("all students", listStudents);
        return "students";
    }

    @GetMapping("/students/{id}")
    public String getStudentDetails(@PathVariable int id, Model model) throws SQLException, UserPrincipalNotFoundException {
        Student student = studentService.getById(id);
        model.addAttribute("student", student);
        return "student-details";
    }

    @PostMapping("students/save")
    public String saveStudent(Student student, RedirectAttributes ra){
        studentService.save(student);
        ra.addFlashAttribute("The student have been saved successfully");
        return "redirect:/students";
    }

    @GetMapping("students/{id}/edit")
    public String editStudentForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) throws SQLException, UserPrincipalNotFoundException {

            Student student=studentService.getById(id);
            model.addAttribute("Student", student);
            model.addAttribute("Edit", "Edit User(ID:"+id+")");

            return "student-form";
    }

    @GetMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        try{
            studentService.delete(id);
            ra.addFlashAttribute("The student ID" + id + "has been deleted");
        }
        catch (UserPrincipalNotFoundException | SQLException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/students";
    }
}

