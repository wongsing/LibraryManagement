package librarymanagement;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Login  extends JFrame {
        //设置全局变量
       public static String username1;
       public static String password1;

    Login(){
        super("登录");
        Container con = getContentPane();
        con.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));

        JTextField username = new JTextField(10);
        JTextField password = new JTextField(10);
        JPanel p1 = new JPanel();
        p1.add(new JLabel("用户名："));
        p1.add(username);

        JPanel p2 = new JPanel();
        p2.add(new JLabel("密  码： "));
        p2.add(password);

        con.add(p1);
        con.add(p2);
        JButton bt1 = new JButton("确定");
        JButton bt2 = new JButton("取消");
        JPanel p3 = new JPanel();
        p3.add(bt1);
        p3.add(bt2);
        con.add(p3);

        setBounds(400,200,250,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //绑定事件，调用数据库检验用户名是否正确
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Myonnection d = new Myonnection();
                 username1 = username.getText();
                 password1 = password.getText();
                if (d.compare(username1,password1)){
                    JOptionPane.showMessageDialog(null,"登录成功！");
                    Login.super.setVisible(false);
                    showMain m = new showMain();
                    m.isEnabled(d.is_admin);
                }
            }
        });
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username.setText("");
                password.setText("");
            }
        });
    }
    public void getUsername1(){
        System.out.println(username1);
    }
}
