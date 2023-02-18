package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {


    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public void createNewUser(User user) {
        userDao.saveAndFlush(user);
    }

    @Override
    public List<User> getAllUsers() {return userDao.findAll();}

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getById(id);
    }//TODO check method(was getReferenceById)
}
