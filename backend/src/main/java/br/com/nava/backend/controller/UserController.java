package br.com.nava.backend.controller;

import br.com.nava.backend.dto.UserDTO;
import br.com.nava.backend.entity.User;
import br.com.nava.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> findAll() {
        List<User> list = userService.getUsers();
        return list.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO findById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return new UserDTO(user);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody UserDTO userDTO) {
        User user = userService.fromDTO(userDTO);
        userService.createUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserDTO userDTO, @PathVariable Integer id) {
        User user = userService.fromDTO(userDTO);
        user.setId(id);
        userService.updateUser(user);
    }
}
