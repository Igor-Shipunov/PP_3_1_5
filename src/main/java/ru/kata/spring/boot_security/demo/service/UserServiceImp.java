package ru.kata.spring.boot_security.demo.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;



@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService, UserDetailsService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
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
    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(matchRoles(user));
        userRepository.saveAndFlush(user);
    }

    private Set<Role> matchRoles(User user) { //костыль для добавления и изменения ролей, иначе TransientObjectException
        Set<Role> newUserRoles = new HashSet<>();
        List<Role> savedRoles = roleRepository.findAll();
        for (Role role : user.getRoles()) {
            if (role.getName().equals(savedRoles.get(0).getName())) {
                newUserRoles.add(savedRoles.get(0));
            } else if (role.getName().equals(savedRoles.get(1).getName())) {
                newUserRoles.add(savedRoles.get(1));
            }
        }
        return newUserRoles;
    }

    @Override
    public List<User> getAllUsers() {return userRepository.findAll();}

    @Override
    @Transactional
    public void updateUser(User user) {
        String currentPassword = userRepository.findById(user.getId()).get().getPassword();
        if (!passwordEncoder.matches(user.getPassword(), currentPassword)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(currentPassword);
        }
        user.setRoles(matchRoles(user));
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
    }
}
