package br.com.nava.backend.service;

import br.com.nava.backend.dto.UserDTO;
import br.com.nava.backend.entity.User;
import br.com.nava.backend.repository.UserRepository;
import br.com.nava.backend.service.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        User updatedUser = getUserById(user.getId());
        updateData(updatedUser, user);
        userRepository.save(updatedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        getUserById(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new ObjectNotFoundException("User" ,userId));
    }

    public void updateData(User updated, User source) {
        updated.setName(source.getName());
        updated.setBankAccounts(source.getBankAccounts());
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName());
    }
}
