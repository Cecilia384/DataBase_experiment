package com.zpy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zpy.pojo.User;
import org.springframework.transaction.annotation.Transactional;

public interface AccountService extends IService<User> {
    boolean login(String user,String password);

}
