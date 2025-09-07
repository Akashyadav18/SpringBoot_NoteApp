package in.sp.main.services;

import in.sp.main.entities.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public User loginUser(String email, String password);
}
