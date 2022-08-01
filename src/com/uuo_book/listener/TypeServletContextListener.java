package com.uuo_book.listener;

import com.uuo_book.bean.Type;
import com.uuo_book.biz.TypeBiz;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author uuo00_n(Hwang Jumbo)
 * @Date 2022/8/1 9:27
 */
@WebListener
public class TypeServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        //1.获取数据库中所有的信息类型
        TypeBiz biz = new TypeBiz();
        List<Type> types = biz.getAll();

        //2.获取application对象
        ServletContext application = servletContextEvent.getServletContext();

        //3.将信息存放在application对象中
        application.setAttribute("type", types);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
