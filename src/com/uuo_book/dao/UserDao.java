package com.uuo_book.dao;

import com.uuo_book.bean.User;
import com.uuo_book.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author uuo00_n(Hwang Jumbo)
 * @Date 2022/7/14 21:15
 * 用户表的数据操作对象
 */
public class UserDao {
    QueryRunner runner = new QueryRunner();

    public User getUser(String name, String pwd) throws SQLException {
        //1.调用DBHelper获取连接对象
        Connection conn = DBHelper.getConnection();

        //2.准备执行sql语句
        String sql = "SELECT * FROM `user` WHERE `name` =? and pwd =? AND state = 1 ";

        //3.调用查询方法，将查询的数据封装成User对象
        User user = runner.query(conn, sql, new BeanHandler<User>(User.class), name, pwd);

        //4.关闭连接对象
        conn.close();

        //5.返回user
        return user;
    }

    /**
     * 修改密码
     *
     * @param id  需要修改密码的用户编号
     * @param pwd 新的密码
     * @return 修改的数据行
     */
    public int modifyPwd(long id, String pwd) throws SQLException {
        String sql = "UPDATE user set pwd = ? WHERE id = ?;";
        Connection conn = DBHelper.getConnection();
        int count = runner.update(conn, sql, pwd, id);
        conn.close();
        return count;
    }


    public static void main(String[] args) {
        User user = null;
        try {
            user = new UserDao().getUser("super", "123");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(user);
    }
}
