package com.vinan.reactproject.controller.restapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinan.reactproject.model.dto.UserDto;
import com.vinan.reactproject.model.entity.User;
import com.vinan.reactproject.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class ApiUser {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/save")
	public ResponseEntity<User> insert(@RequestBody User entity) {
		User user = new User();
		user.setId(entity.getId());
		user.setEmail(entity.getEmail());
		user.setPhone(entity.getPhone());
		user.setPassword(entity.getPassword());
		return new ResponseEntity<User>(userRepository.save(entity), HttpStatus.OK);
	}

	@GetMapping("/get-all")
	public List<User> getUser() {
		return userRepository.findAll();
	}

	@GetMapping("/{idAja}")
	public UserDto getUserById(@PathVariable Integer idAja) {
		User user = userRepository.findById(idAja).get();
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setPhone(user.getPhone());
		userDto.setPassword(user.getPassword());

		return userDto;
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @Valid @RequestBody UserDto userDto) {

		User user = userRepository.findById(id).get();

		user.setId(userDto.getId());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		user.setPassword(userDto.getPassword());

		final User updateUser = userRepository.save(user);

		userDto.setId(updateUser.getId());
//		userDto.setEmail(updateUser.getEmail());
//		userDto.setPhone(updateUser.getPhone());
//		userDto.setPassword(updateUser.getPassword());

		return ResponseEntity.ok(userDto);
	}

	@PostMapping("/check-user")
	public ResponseEntity<Boolean> checkUser(@RequestBody User user) {
		List<User> userCheckList = userRepository.findAll();
		for (int i = 0; i < userCheckList.size(); i++) {
			if (user.getEmail().equals(userCheckList.get(i).getEmail())
					&& user.getPassword().equals(userCheckList.get(i).getPassword())) {
				return ResponseEntity.ok(Boolean.TRUE);
			}
		}
		return ResponseEntity.ok(Boolean.FALSE);
	}

	@GetMapping("/getAll") // tidak bisa
	public ResponseEntity<List<User>> findAll() {
		List<User> list = userRepository.findAll();

		for (int i = 0; i < list.size(); i++) {
			User user = new User();
			user.setEmail(list.get(i).getEmail());
			user.setPhone(list.get(i).getPhone());
			user.setPassword(list.get(i).getPassword());
			list.add(user);
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

}
