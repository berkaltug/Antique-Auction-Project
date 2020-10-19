package com.scopic.antiqueauction.domain.converter;

import com.scopic.antiqueauction.domain.entity.User;
import com.scopic.antiqueauction.domain.request.RegistrationRequest;

public class UserConverter {

    public static User convert(final RegistrationRequest request){
        final User user=new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }
}
