package in.sp.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Task;
import in.sp.main.entities.User;
import in.sp.main.repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task createTask(String title, String desc, User user) {
		Task task = new Task();
		task.setCompleted(false);
		task.setTitle(title);
		task.setDesc(desc);
		task.setUser(user);
		return taskRepository.save(task);
	}

	@Override
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}

	@Override
	public void toggleTask(Long id) {
		Task task = taskRepository.findById(id).orElse(null);
		task.setCompleted(!task.isCompleted());
		taskRepository.save(task);
	}

	@Override
	public Task getTaskbyId(Long id) {
		return taskRepository.findById(id).orElse(null);
	}

	@Override
	public Task updateTask(Task task, Long id) {
		Task existingTask = taskRepository.findById(id).orElse(null);
		if(existingTask != null) 
		{
			existingTask.setTitle(task.getTitle());
			existingTask.setDesc(task.getDesc());
			existingTask.setCompleted(task.isCompleted());
		}
		return taskRepository.save(existingTask);
	}

	@Override
	public List<Task> getTasksByUser(User user) {
		return taskRepository.findByUser(user);
	}
	
	

}
