package ru.kata.spring.boot_security.demo.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService, UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }
        return user.get();
    }


    @Override
    @Transactional
    public void createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAllUsers() {return userRepository.findAll();}

    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getById(id);
    }//TODO check method(was getReferenceById)
}
