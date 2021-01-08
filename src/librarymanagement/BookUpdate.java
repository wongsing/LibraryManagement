package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookUpdate extends JFrame {
    private String data[] = {"科技", "文学", "社科", "其他"};

    //构造方法
    BookUpdate() {
        super("修改图书");
        Container con = getContentPane();
        con.setLayout(new FlowLayout());
        JTextField bookid1 = new JTextField(10);
        JTextField bookid = new JTextField(10);
        JTextField bookname = new JTextField(10);
        JComboBox booktype = new JComboBox(data);
        JTextField author = new JTextField(10);
        JTextField translator = new JTextField(10);
        JTextField publisher = new JTextField(10);
        JTextField publish_time = new JTextField(10);
        JTextField price = new JTextField(10);
        JTextField stock = new JTextField(10);

        //第一行
        JPanel p7 = new JPanel();
        p7.add(new JLabel("图书编号"));
        JButton bt3 = new JButton("查询");
        p7.add(bookid1);
        p7.add(bt3);
        //第二行
        JPanel p1 = new JPanel();
        p1.add(new JLabel("图书编号"));
        p1.add(bookid);
        p1.add(new JLabel("图书名称"));
        p1.add(bookname);
        //第三行
        JPanel p2 = new JPanel();
        p2.add(new JLabel("图书类别"));
        p2.add(booktype);
        p2.add(new JLabel("作者  "));
        p2.add(author);
        //第四行
        JPanel p3 = new JPanel();
        p3.add(new JLabel("译者  "));
        p3.add(translator);
        p3.add(new JLabel("出版社"));
        p3.add(publisher);
        //第五行
        JPanel p4 = new JPanel();
        p4.add(new JLabel("出版时间"));
        p4.add(publish_time);
        p4.add(new JLabel("定价  "));
        p4.add(price);
        //第刘行
        JPanel p5 = new JPanel();
        p5.add(new JLabel("库存  "));
        p5.add(stock);
        //底部按钮
        JButton bt1 = new JButton("保存");
        JButton bt2 = new JButton("关闭");

        JPanel p6 = new JPanel();
        p6.add(bt1);
        p6.add(bt2);

        con.add(p7);
        con.add(p1);
        con.add(p2);
        con.add(p3);
        con.add(p4);
        con.add(p5);
        con.add(p6);

        setBounds(300, 200, 400, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //保存按钮
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                Myonnection m = new Myonnection();
                String id = bookid.getText();
                String name = bookname.getText();
                String type = booktype.getSelectedItem().toString();
                String author1 = author.getText();
                String translator1 = translator.getText();
                String publisher1 = publisher.getText();
                String publish_time1 = publish_time.getText();
                double price1 = Double.parseDouble(price.getText());
                int stock1 = Integer.parseInt(stock.getText());
                flag = m.executeUpdate(id, name, type, author1, translator1, publisher1, publish_time1, price1, stock1);
                if (flag) {
                    JOptionPane.showMessageDialog(null, "修改成功！");
                } else {
                    JOptionPane.showMessageDialog(null, "修改失败！");
                }

            }
        });

        //关闭按钮
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookUpdate.super.setVisible(false);
            }
        });

        //查询按钮
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                Myonnection m = new Myonnection();
                String id1 = bookid1.getText();    //判断是否存在此书
                System.out.println(id1);
                if (m.executeQuery(id1)) {
                    bookid.setText(id1 + "");
                    bookname.setText(m.bookname);
                    String str = "\" + m.booktype+\"";
                    booktype.setSelectedItem(str);
                    author.setText(m.author);
                    translator.setText(m.translator);
                    publisher.setText(m.publisher);
                    publish_time.setText(m.publish_time);
                    price.setText(m.price + "");
                    stock.setText(m.stock + "");
                }
            }
        });
    }
}
