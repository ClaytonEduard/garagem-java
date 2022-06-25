package com.br.garagem.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public Optional<User> getUserByNickName() {
        return this.repo.findById(1);
    }

    public void createOrUpdate(User user) {
        this.repo.save(user);
    }

}
