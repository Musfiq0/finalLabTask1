import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller

public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){this.studentService=studentService;}

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @RequestMapping("/students")
    public String getAllStudents(Model model) {
        model.addAttribute("students", students);
        return "students";
    }

    @RequestMapping("/{id}")
    public String getStudentDetails(@PathVariable int id, Model model) {
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));
        model.addAttribute("student", student);
        return "student-details";
    }

    @RequestMapping("/{id}/edit")
    public String editStudentForm(@PathVariable int id, Model model) {
        model.addAttribute("student", student);
        return "edit-student";
    }

    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable int id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
}
