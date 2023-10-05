package com.example.demo.dao;

import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }
    @Transactional
    @Override
    public void updateUser(User user, long id) {
        User newDataUser = entityManager.find(User.class, user.getId());
        entityManager.detach(newDataUser);
        newDataUser.setName(user.getName());
        newDataUser.setLastName(user.getLastName());
        newDataUser.setAge(user.getAge());
        entityManager.merge(user);

    }

    @Override
    public User getUser(long id) {
        Query query = entityManager.createQuery("from User u WHERE u.id=:id");
        query.setParameter("id", id);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers(String number) {
        List<User> list = entityManager.createQuery("from User ").getResultList();
        int counter = list.size();
        if (number != null) {
            counter = Integer.parseInt(number);
        }
        return list.stream().limit(counter).collect(Collectors.toList());
    }
}