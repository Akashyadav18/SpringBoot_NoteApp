package in.sp.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.Task;
import in.sp.main.entities.User;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> 
{
	List<Task> findByUser(User user);
}
