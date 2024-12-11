package com.company.project.service;

import com.company.project.entity.Result;
import com.company.project.entity.User;

public interface IUserService {

    Result register(User user);

    Result login(User user);
}
