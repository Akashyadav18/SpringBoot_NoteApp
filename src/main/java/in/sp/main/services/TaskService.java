package in.sp.main.services;

import java.util.List;

import in.sp.main.entities.Task;
import in.sp.main.entities.User;

public interface TaskService {
	
	public List<Task> getAllTasks();
	
	public Task createTask(String title, String desc, User user);
	
	public void deleteTask(Long id);
	
	public void toggleTask(Long id);
	
	public Task getTaskbyId(Long id);
	
	public Task updateTask(Task task, Long id);
	
	public List<Task> getTasksByUser(User user);
}
