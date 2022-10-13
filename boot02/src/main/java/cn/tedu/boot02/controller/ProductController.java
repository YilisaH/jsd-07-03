package cn.tedu.boot02.controller;

import cn.tedu.boot02.entity.Product;
import cn.tedu.boot02.util.DBUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Controller
public class InsertController {
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(Product product){
        System.out.println("product = " + product);
        try (
                Connection cnn = DBUtil.getConnection()
                ){
           String sql = "insert into product values (null,?,?,?)";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1,product.getTitle());
            ps.setDouble(2,product.getPrice());
            ps.setInt(3,product.getNum());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "添加完成";
    }
}
