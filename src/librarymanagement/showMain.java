package librarymanagement;


import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public  class showMain  extends JFrame {

    JTextArea ta = new JTextArea();
    // 创建菜单条和菜单项
    JMenuBar mb = new JMenuBar();
    JMenu m1 = new JMenu(" 图书信息维护");
    JMenu m2 = new JMenu(" 读者信息维护");
    JMenu m3 = new JMenu(" 借阅管理");
    JMenu m4 = new JMenu(" 查询管理");
    JMenu m5 = new JMenu(" 系统管理");
    //图书信息维护
    JMenuItem book_insert = new JMenuItem(" 添加");
    JMenuItem book_update = new JMenuItem(" 修改");
    JMenuItem book_delete = new JMenuItem(" 删除");
    JMenuItem book_query = new JMenuItem(" 查询");
    //读者信息维护
    JMenuItem reader_insert = new JMenuItem(" 添加");
    JMenuItem reader_update = new JMenuItem(" 修改");
    JMenuItem reader_delete = new JMenuItem(" 删除");
    JMenuItem reader_query = new JMenuItem(" 查询");
    //借阅管理
    JMenuItem borrow = new JMenuItem(" 借书");
    JMenuItem back = new JMenuItem(" 还书");
    //查询管理
    JMenuItem book_s = new JMenuItem(" 图书信息查询");
    JMenuItem reader_s = new JMenuItem(" 读者信息查询");
    //系统管理
    JMenuItem reader_c = new JMenuItem(" 修改用户密码");

    // 构建方法，初始化
    public showMain() {
        // 标题名
        setTitle(" 图书管理系统");
        // 窗口宽高
        setSize(800, 500);

        // 给菜单添加菜单项
        m1.add(book_insert);
        m1.add(book_update);
        m1.add(book_delete);
        m1.add(book_query);

        m2.add(reader_insert);
        m2.add(reader_update);
        m2.add(reader_delete);
        m2.add(reader_query);

        m3.add(borrow);
        m3.add(back);
        m4.add(book_s);
        m4.add(reader_s);
        m5.add(reader_c);

        // 添加分隔条
        m1.addSeparator();
        m2.addSeparator();
        m3.addSeparator();
        m4.addSeparator();
        m5.addSeparator();

        // 把菜单添加到菜单条上
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);
        mb.add(m5);
        // 把菜单条添加到 Jframe 窗口上
        setJMenuBar(mb);
        // 设置窗口打开的位置
        setLocation(200, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //添加图书
        book_insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookAdd bookAdd = new BookAdd();
            }
        });
        //修改图书
        book_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookUpdate bookUpdate  = new BookUpdate();
            }
        });
        //删除图书
        book_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookDelete bookDelete = new BookDelete();
            }
        });

        //添加读者信息
        reader_insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReaderAdd readerAdd = new ReaderAdd();
            }
        });
        //修改读者信息
        reader_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReaderUpdate readerUpdate = new ReaderUpdate();
            }
        });
        //删除读者信息
        reader_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReaderDelete readerDelete = new ReaderDelete();
            }
        });

        //修改密码
        reader_c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdatePassword updatePassword = new UpdatePassword();
                dispose();
            }
        });

        //借书
        borrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Borrow borrow = new Borrow();
            }
        });

        //还书
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Back back = new Back();
            }
        });

        //图书信息查询
        book_s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookQuery bookQuery = new BookQuery();
            }
        });
        //同上
        book_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookQuery bookQuery = new BookQuery();
            }
        });
        //读者信息查询
        reader_s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReaderQuery readerQuery = new ReaderQuery();
            }
        });
        //同上
        reader_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReaderQuery readerQuery = new ReaderQuery();
            }
        });
    }
        //读者信息查询
    public void isEnabled(int a){
        if (a == 0){
            m1.setEnabled(false);
            m2.setEnabled(false);
        }
    }
    public static void main(String[] args) {
      Login login = new Login();
    }
}
