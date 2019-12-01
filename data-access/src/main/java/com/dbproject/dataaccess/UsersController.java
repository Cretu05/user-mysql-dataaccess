package com.dbproject.dataaccess;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/users")
public class UsersController {
	
	@Autowired
	UsersRepository usersRepository;
	
	@GetMapping(value = "/all")
	public List<Users> getAll(){
		return usersRepository.findAll();
	}
	
	@PostMapping(value = "/load")
	public String newUser(@RequestBody final Users user) {
		usersRepository.save(user);
		return "User saved";
	}
	
	
	@GetMapping(value = "/{id}")
	public Optional<Users> getById (@PathVariable Integer id) {
		return usersRepository.findById(id);
	}
	
	@PutMapping(value = "/{id}")
	public Users replaceUser(@RequestBody Users newUser, @PathVariable Integer id) {
		return usersRepository.findById(id)
			.map(user -> {
				user.setName(newUser.getName());
				user.setEmail(newUser.getEmail());
				return usersRepository.save(user);
			})
			.orElseGet(() -> {
				newUser.setId(id);
				return usersRepository.save(newUser);
			});
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteUser(@PathVariable Integer id) {
		usersRepository.deleteById(id);
		return "User removed";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
