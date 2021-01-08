package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReaderUpdate extends JFrame {
    ReaderUpdate(){
        super("修改读者信息");
        Container con = getContentPane();
        con.setLayout(new FlowLayout());

        JTextField readerid1 = new JTextField(10);
        JTextField readerid = new JTextField(10);
        JTextField readername = new JTextField(10);
        JTextField readtype = new JTextField(10);
        JTextField sex = new JTextField(10);
        JTextField max_num= new JTextField(10);
        JTextField days_num = new JTextField(10);

        //第一行
        JPanel p5 = new JPanel();
        p5.add(new JLabel("读者编号"));
        JButton bt3 = new JButton("查询");
        p5.add(readerid1);
        p5.add(bt3);
        //第二行
        JPanel p1 = new JPanel();
        p1.add(new JLabel("读者编号"));
        p1.add(readerid);
        p1.add(new JLabel("读者姓名"));
        p1.add(readername);
        //第三行
        JPanel p2 = new JPanel();
        p2.add(new JLabel("读者类别"));
        p2.add(readtype);
        p2.add(new JLabel("性别  "));
        p2.add(sex);
        //第四行
        JPanel p3 = new JPanel();
        p3.add(new JLabel("最大可借数  "));
        p3.add(max_num);
        p3.add(new JLabel("最大可借天数"));
        p3.add(days_num);

        //底部按钮
        JButton bt1 = new JButton("保存");
        JButton bt2 = new JButton("关闭");
        JPanel p4 = new JPanel();
        p4.add(bt1);
        p4.add(bt2);

        con.add(p5);
        con.add(p1);
        con.add(p2);
        con.add(p3);
        con.add(p4);

        setBounds(300, 200, 400, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //保存按钮
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                Myonnection m = new Myonnection();
                String id = readerid.getText();
                String name = readername.getText();
                String type = readtype.getText();
                String sex1 = sex.getText();
                int max_num1 = Integer.parseInt(max_num.getText());
                int days_num1  = Integer.parseInt(days_num.getText());
                flag = m.executeUpdate_reader(id,name,type,sex1,max_num1,days_num1);
                if (flag){
                    JOptionPane.showMessageDialog(null,"添加成功！");
                }else{
                    JOptionPane.showMessageDialog(null,"添加失败！");
                }
            }
        });
        //关闭按钮
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReaderUpdate.super.setVisible(false);
            }
        });
        //查询按钮
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                Myonnection m = new Myonnection();
                String id1 = readerid1.getText();    //判断是否存在此书
                if (m.executeQuery_reader(id1)) {
                    readerid.setText(id1 + "");
                    readername.setText(m.readername);
                    readtype.setText(m.readtype);
                    sex.setText(m.sex);
                    max_num.setText(m.max_num + "");
                    days_num.setText(m.days_num + "");
                }
            }
        });
    }
}
