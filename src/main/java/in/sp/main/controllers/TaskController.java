package in.sp.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sp.main.entities.Task;
import in.sp.main.entities.User;
import in.sp.main.services.TaskService;
import jakarta.servlet.http.HttpSession;

@Controller
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/taskPage")
	public String openTaskPage(Model model, HttpSession session)
	{
		User loggedInUser = (User) session.getAttribute("sessionUser");
		if(loggedInUser == null)
		{
			return "redirect:/loginPage";
		}
		
		List<Task> tasks = taskService.getTasksByUser(loggedInUser);
		model.addAttribute("tasks", tasks);
		return "task";
	}
	
	@PostMapping("/submitTask")
	public String createTask(@RequestParam String title, String desc, HttpSession session)
	{
		User loggedInUser = (User) session.getAttribute("sessionUser");
		
		taskService.createTask(title, desc, loggedInUser);
		return "redirect:/taskPage";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTask(@PathVariable Long id)
	{
		taskService.deleteTask(id);
		return "redirect:/taskPage";
	}
	
	@GetMapping("/toggle/{id}")
	public String toggleTask(@PathVariable Long id)
	{
		taskService.toggleTask(id);
		return "redirect:/taskPage";
	}
	
	@GetMapping("/updateForm/{id}")
	public String openUpdateForm(@PathVariable Long id, Model model)
	{
		Task task = taskService.getTaskbyId(id);
		if(task != null)
		{
			model.addAttribute("task", task);
			return "updateForm";
		}
		else {
			return "redirect:/taskPage";
		}
	}
	
	@PostMapping("/update/{id}")
	public String updateTask(@PathVariable Long id, @ModelAttribute("task") Task task)
	{
		taskService.updateTask(task, id);
		return "redirect:/taskPage";
	}
}
