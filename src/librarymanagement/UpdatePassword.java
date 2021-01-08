package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePassword extends JFrame {
    //构造方法
    UpdatePassword(){
        super("修改密码");
        Container con = getContentPane();
        con.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));

        JTextField password1 = new JTextField(10);
        JTextField password2 = new JTextField(10);
        JPanel p1 = new JPanel();
        p1.add(new JLabel("请输入新密码"));
        p1.add(password1);

        JPanel p2 = new JPanel();
        p2.add(new JLabel("再次确认新密码"));
        p2.add(password2);

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

        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login l = new Login();
                Myonnection m = new Myonnection();
                if (password1.getText().equals(password2.getText()))
                {
                    String pa = password1.getText();
                    if (m.executeUpdate_password(l.username1,pa)){
                        JOptionPane.showMessageDialog(null,"密码修改成功！");
                        UpdatePassword.super.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null,"密码修改失败！");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"两次密码输入不一致，请再次输入！");
                }
            }
        });
            bt2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    UpdatePassword.super.setVisible(false);
                    showMain s = new showMain();
                }
            });
    }
}
