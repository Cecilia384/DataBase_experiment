package com.zpy.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpy.mapper.AccountMapper;
import com.zpy.pojo.User;
import com.zpy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
@Service
@Transactional
public  class AccountServiceImpl extends ServiceImpl<AccountMapper, User> implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Override
    public boolean login(String username, String password) {
        QueryWrapper<User>qw=new QueryWrapper<>();
        qw.eq("username",username);
        User user = accountMapper.selectOne(qw);
        if (user==null){
            return false;
        }
        String userPwd = user.getPassword();
        String s = DigestUtil.md5Hex(password);
        if (Objects.equals(userPwd,s)){
            return true;
        }else {
            return false;
        }
    }

}
