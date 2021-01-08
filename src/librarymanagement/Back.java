package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Back extends JFrame {
    //构造方法
    Back(){
        super("还回图书");
        Container con = getContentPane();
        con.setLayout(new FlowLayout());

        JTextField bookid = new JTextField(10);
        JTextField readerid = new JTextField(10);
        JButton bt1 = new JButton("查询");
        //第一行
        JPanel p1 = new JPanel();
        con.add(p1,BorderLayout.NORTH);
        p1.setLayout(new FlowLayout(FlowLayout.CENTER));
        p1.add(new JLabel("图书编号"));
        p1.add(bookid);
        p1.add(new JLabel("读者编号"));
        p1.add(readerid);
        p1.add(bt1);
        //第二行
        JPanel p2 = new JPanel();
        p2.add(new JLabel("------------------------------------------------图书信息-------------------------------------------------"));
        con.add(p2);
        p2.setLayout(new FlowLayout(FlowLayout.CENTER));

        //第三行
        JPanel p3 = new JPanel();
        p3.add(new JLabel("图书名称："));
        JTextField bookname = new JTextField(10);
        bookname.setBackground(Color.LIGHT_GRAY);
        bookname.setEditable(false);
        p3.add(bookname);
        p3.add(new JLabel("作者：    "));
        JTextField author = new JTextField(10);
        author.setBackground(Color.LIGHT_GRAY);
        author.setEditable(false);
        p3.add(author);
        con.add(p3,BorderLayout.WEST);
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        //第四行
        JPanel p4 = new JPanel();
        p4.add(new JLabel("出版社："));
        JTextField publisher = new JTextField(10);
        publisher.setBackground(Color.LIGHT_GRAY);
        publisher.setEditable(false);
        p4.add(publisher);
        p4.add(new JLabel("出版时间："));
        JTextField publish_time = new JTextField(10);
        publish_time.setBackground(Color.LIGHT_GRAY);
        publish_time.setEditable(false);
        p4.add(publish_time);
        con.add(p4,BorderLayout.WEST);
        p4.setLayout(new FlowLayout(FlowLayout.LEFT));
        //第五行
        JPanel p5 = new JPanel();
        p5.add(new JLabel("定价：  "));
        JTextField price = new JTextField(10);
        price.setBackground(Color.LIGHT_GRAY);
        price.setEditable(false);
        p5.add(price);
        p5.add(new JLabel("库存数量："));
        JTextField stock = new JTextField(10);
        stock.setBackground(Color.LIGHT_GRAY);
        stock.setEditable(false);
        p5.add(stock);
        con.add(p5,BorderLayout.WEST);
        p5.setLayout(new FlowLayout(FlowLayout.LEFT));
        //第六行
        JPanel p6 = new JPanel();
        p6.add(new JLabel("------------------------------------------------读者信息-------------------------------------------------"));
        con.add(p6);
        p6.setLayout(new FlowLayout(FlowLayout.CENTER));
        //第七行
        JPanel p7 = new JPanel();
        p7.add(new JLabel("读者姓名："));
        JTextField readername= new JTextField(10);
        readername.setBackground(Color.LIGHT_GRAY);
        readername.setEditable(false);
        p7.add(readername);
        p7.add(new JLabel("读者类型："));
        JTextField readtype = new JTextField(10);
        readtype.setBackground(Color.LIGHT_GRAY);
        readtype.setEditable(false);
        p7.add(readtype);
        con.add(p7,BorderLayout.WEST);
        p7.setLayout(new FlowLayout(FlowLayout.LEFT));
        //第八行
        JPanel p8 = new JPanel();
        p8.add(new JLabel("最大可借数："));
        JTextField max_num= new JTextField(10);
        max_num.setBackground(Color.LIGHT_GRAY);
        max_num.setEditable(false);
        p8.add(max_num);
        p8.add(new JLabel("最大可借天数："));
        JTextField days_num = new JTextField(10);
        days_num.setBackground(Color.LIGHT_GRAY);
        days_num.setEditable(false);
        p8.add(days_num);
        con.add(p8,BorderLayout.WEST);
        p8.setLayout(new FlowLayout(FlowLayout.LEFT));
        //第九行
        JPanel p9 = new JPanel();
        p9.add(new JLabel("------------------------------------------------还书信息-------------------------------------------------"));
        con.add(p9);
        p9.setLayout(new FlowLayout(FlowLayout.CENTER));
        //第十行
        JPanel p10 = new JPanel();
        p10.add(new JLabel("还书日期："));
        JTextField back_date= new JTextField(20);
        back_date.setBackground(Color.LIGHT_GRAY);
        back_date.setEditable(false);
        p10.add(back_date);
        con.add(p10,BorderLayout.WEST);
        p10.setLayout(new FlowLayout(FlowLayout.LEFT));

        //底部按钮
        JButton bt2 = new JButton("还书");
        JButton bt3 = new JButton("关闭");
        JPanel p11 = new JPanel();
        p11.add(bt2);
        p11.add(bt3);
        con.add(p11);

        setBounds(300,200,500,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Myonnection m = new Myonnection();
                String book_id = bookid.getText();
                String reader_id = readerid.getText();
                //查询图书信息
                if (m.executeQuery(book_id)){
                    bookname.setText(m.bookname);
                    author.setText(m.author);
                    publisher.setText(m.publisher);
                    publish_time.setText(m.publish_time);
                    price.setText(m.price + "");
                    stock.setText(m.stock + "");
                }
                //查询读者信息
                if (m.executeQuery_reader(reader_id)) {
                    readername.setText(m.readername);
                    readtype.setText(m.readtype);
                    max_num.setText(m.max_num + "");
                    days_num.setText(m.days_num + "");
                }
                //获取还书日期
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                back_date.setText(df.format(new Date()));   //new Date()为获取当前系统时间
            }
        });

        //还书按钮
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0;
                Myonnection m = new Myonnection();
                String bookid1 = bookid.getText();
                String readerid1 = readerid.getText();
                boolean borrowed_book = false;      //是否可借了此书
                int book_num = 0 ;                  //现存书的数量
                String if_back = "是";               //书是否已还
                //访问borrow表 查询是否可借书 与 该书是否已还
                if (m.executeQuery_reader_borrow(bookid1,readerid1)){
                    borrowed_book = m.borrowed_book;
                    if_back = m.if_back1;
                }
                if (borrowed_book && if_back.equals("否")){
                    JOptionPane.showMessageDialog(null,"可以还书!");
                    if (m.executeDelete_borrow(bookid1,readerid1) &&m.executeUpdate_Book_stock("还书",bookid1))
                        JOptionPane.showMessageDialog(null,"还书成功!");
                }else{
                    JOptionPane.showMessageDialog(null,"未借此书!");
                }
            }
        });

        //关闭按钮
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Back.super.setVisible(false);
            }
        });
    }
}
