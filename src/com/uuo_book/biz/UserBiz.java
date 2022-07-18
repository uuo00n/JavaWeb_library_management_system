package com.uuo_book.biz;

import com.uuo_book.bean.User;
import com.uuo_book.dao.UserDao;

import java.sql.SQLException;

/**
 * @author uuo00_n(Hwang Jumbo)
 * @Date 2022/7/14 21:50
 */
public class UserBiz {
    //构建UserDao对象
    UserDao userDao = new UserDao();

    public User getUser(String name, String pwd) {
        //传递
        try {
            return userDao.getUser(name, pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public int modifyPwd(long id, String pwd) {
        int count = 0;
        try {
            count = userDao.modifyPwd(id, pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
}

