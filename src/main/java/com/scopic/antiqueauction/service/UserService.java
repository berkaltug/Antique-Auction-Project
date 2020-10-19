package com.scopic.antiqueauction.service;

import com.scopic.antiqueauction.domain.entity.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findLoggedInUser();

}
