package com.project.controller;

import com.project.model.ToDo;
import com.project.service.ToDoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping("/addToDoItem")
    public String addToDoItem(Model model) {
        model.addAttribute("todo", new ToDo());

        return "addToDoItem";
    }

    @GetMapping({"/", "viewToDoList"})
    public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
        log.debug("Inside viewAllToDoItems.");
        model.addAttribute("list", toDoService.getAllToDoItems());
        model.addAttribute("message", message);

        return "viewToDoList";
    }

    @PostMapping("/saveToDoItem")
    public String saveToDoItem(@ModelAttribute ToDo toDo, RedirectAttributes redirectAttributes) {
        log.debug("Inside saveToDoItem. toDoDTO: {}", toDo);

        if (toDoService.updateOrSaveToDoItemInDB(toDo)) {
            redirectAttributes.addAttribute("code", 0);
            redirectAttributes.addAttribute("message", "Save Success");
            return "redirect:/viewToDoList";
        }

        redirectAttributes.addAttribute("code", -1);
        redirectAttributes.addAttribute("message", "Save Failure");
        return "redirect:/addToDoItem";
    }

    @GetMapping("/deleteToDoItem/{id}")
    public String deleteToDoItem(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            toDoService.deleteById(id);
            redirectAttributes.addAttribute("code", 0);
            redirectAttributes.addAttribute("message", "Delete Success");
        } catch (Exception exception) {
            redirectAttributes.addAttribute("code", -1);
            redirectAttributes.addAttribute("message", "Delete Failure");
        }
        return "redirect:/viewToDoList";
    }

    @GetMapping("/updateToDoStatus/{id}")
    public String updateToDoStatus(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        log.debug("updateToDoStatus id: {}", id);
        if (toDoService.updateStatus(id)) {
            redirectAttributes.addFlashAttribute("message", "Update Success");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Update Failure");
        return "redirect:/viewToDoList";

    }

    @GetMapping("/editToDoItem/{id}")
    public String editToDoItem(@PathVariable Integer id, Model model) {
        model.addAttribute("todo", toDoService.findItemsById(id));
        return "editToDoItem";
    }

    @PostMapping("/editSaveToDoItem")
    public String editSaveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
        log.debug("Todo :{}", todo);
        if (toDoService.updateOrSaveToDoItemInDB(todo)) {
            redirectAttributes.addFlashAttribute("message", "Edit Success");
            return "redirect:/viewToDoList";
        }

        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/editToDoItem/" + todo.getId();
    }
}