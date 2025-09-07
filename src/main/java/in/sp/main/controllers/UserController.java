package in.sp.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.sp.main.entities.User;
import in.sp.main.services.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/regPage")
	public String openRegPage(Model model)
	{
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/submitReg")
	public String submitRegForm(@ModelAttribute("user") User user)
	{
		User regUser = userService.registerUser(user);
		if(regUser != null)
		{
			return "login";
		}
		return "register";
	}
	
	@GetMapping("/loginPage")
	public String openLoginPage(Model model)
	{
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/submitLogin")
	public String submitLoginForm(@ModelAttribute("user") User user, HttpSession session)
	{
		User validUser = userService.loginUser(user.getEmail(), user.getPassword());
		if(validUser != null)
		{
			session.setAttribute("sessionUser", validUser);
			return "redirect:/taskPage";
		}
		return "login";
	}
	

	@GetMapping("/logout")
	public String logoutUser(HttpSession session) 
	{
		session.invalidate();
		return "redirect:/loginPage";
	}
}
