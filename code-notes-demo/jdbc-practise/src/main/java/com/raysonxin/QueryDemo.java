package com.raysonxin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @className: QueryDemo.java
 * @author: raysonxin
 * @date: 2020/2/5 3:30 下午
 * @email: raysonxin@163.com
 * @description: TODO
 **/
public class QueryDemo {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/rsx_base";
        String userName = "root";
        String pwd = "761217xin";

        Connection connection = DriverManager.getConnection(url, userName, pwd);
        String sql = "select count(*) as total from base_sku";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            int count = resultSet.getInt("total");
            System.out.println(count);
        }
        statement.close();
        connection.close();
    }
}
