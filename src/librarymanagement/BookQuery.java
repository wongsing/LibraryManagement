package librarymanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Vector;

public class BookQuery extends JFrame {

    //表格的结构
    public JTable tbl;
    public Object data[][];
    public Object title[] = {"图书编号", "图书名称", "图书类别", "作者", "译者", "出版社", "出版日期", "定价", "库存数量"};
    public final int ROW = 20, COL = 9;

    //构造方法
    BookQuery() {
        super("图书信息查询");
        Container con = getContentPane();
        con.setLayout(new FlowLayout());

        JTextField bookname = new JTextField(10);
        JTextField author = new JTextField(10);
        JTextField publisher = new JTextField(10);
        JTextField publish_time = new JTextField(10);

        //第一行
        JPanel p1 = new JPanel();
        p1.add(new JLabel("图书名称"));
        p1.add(bookname);
        p1.add(new JLabel("作    者"));
        p1.add(author);
        //第二行
        JPanel p2 = new JPanel();
        p2.add(new JLabel("出 版 社"));
        p2.add(publisher);
        p2.add(new JLabel("出版时间（年-月）"));
        p2.add(publish_time);
        //中间按钮
        JButton bt1 = new JButton("查询");
        JButton bt2 = new JButton("关闭");
        JPanel p3 = new JPanel();
        p3.add(bt1);
        p3.add(bt2);

        con.add(p1);
        con.add(p2);
        con.add(p3);
        //初始化表格
        data = new Object[ROW][COL];
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++)
                data[i][j] = "";
        tbl = new JTable(data, title);
        tbl.setRowHeight(20);
        //调整表格列宽
        TableColumn col1 =  tbl.getColumn("图书名称");
        col1.setPreferredWidth(100);
        TableColumn col2 =  tbl.getColumn("出版社");
        col2.setPreferredWidth(100);
        con.add(new JScrollPane(tbl));

        setBounds(400, 100, 600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Myonnection m = new Myonnection();
                String bookname1 = bookname.getText();
                String author1 = author.getText();
                String publisher1 = publisher.getText();
                String publish_time1 = publish_time.getText();
                String chose = "全选";

                boolean flag = true;    //判断是否需要重新输入
                //判断那个输入框为空
                if (bookname1.equals("")&&!author1.equals("")&&!publisher1.equals("")&&!publish_time1.equals("") ) {
                    chose = "书名为空";
                }else if (!bookname1.equals("")&&author1.equals("")&&!publisher1.equals("")&&!publish_time1.equals("") ) {
                    chose = "作者为空";
                }else if (!bookname1.equals("")&&!author1.equals("")&&publisher1.equals("")&&!publish_time1.equals("") ) {
                    chose = "出版社为空";
                }else if (!bookname1.equals("")&&!author1.equals("")&&!publisher1.equals("")&&publish_time1.equals("") ) {
                    chose = "出版时间为空";
                }else if ((bookname1.equals("")&&author1.equals("")&&publisher1.equals("")&&publish_time1.equals(""))
                || (!bookname1.equals("")&&!author1.equals("")&&!publisher1.equals("")&&!publish_time1.equals("")) ){
                    chose = "全不选";
                }else{
                    JOptionPane.showMessageDialog(null, "请重新填写信息，只能省略一处或者全部省略！");
                    flag = false;
                }
                if (flag){
                    BookQuery.super.setVisible(false);
                    if (m.executeQuery_book_query(chose, bookname1, author1, publisher1, publish_time1))
                        JOptionPane.showMessageDialog(null, "查询成功");
                    else
                        JOptionPane.showMessageDialog(null, "查询失败");
                }
            }
        });
        //关闭按钮
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookQuery.super.setVisible(false);
            }
        });
    }
}
