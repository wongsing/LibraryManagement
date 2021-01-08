package librarymanagement;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReaderQuery extends JFrame {
    //表格的结构
    public JTable tbl;
    public Object data[][];
    public Object title[] = {"读者编号", "读者姓名", "读者类别", "性别", "最大可借数", "最大可借天数"};
    public final int ROW = 10, COL = 6;
    //构造方法
    ReaderQuery(){
        super("读者信息查询");
        Container con = getContentPane();
        con.setLayout(new FlowLayout());
        JTextField readid = new JTextField(10);
        JTextField readername = new JTextField(10);


        //第一行
        JPanel p1 = new JPanel();
        p1.add(new JLabel("读者编号"));
        p1.add(readid);
        p1.add(new JLabel("读者姓名"));
        p1.add(readername);

        //中间按钮
        JButton bt1 = new JButton("查询");
        JButton bt2 = new JButton("关闭");
        JPanel p2 = new JPanel();
        p2.add(bt1);
        p2.add(bt2);

        con.add(p1);
        con.add(p2);

        //初始化表格
        data = new Object[ROW][COL];
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++)
                data[i][j] = "";
        tbl = new JTable(data, title);
        tbl.setRowHeight(20);
        //调整表格列宽
//        TableColumn col1 =  tbl.getColumn("图书名称");
//        col1.setPreferredWidth(100);
//        TableColumn col2 =  tbl.getColumn("出版社");
//        col2.setPreferredWidth(100);
        con.add(new JScrollPane(tbl));

        setBounds(400, 100, 600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //查询按钮
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Myonnection m = new Myonnection();
                String readername1 = readername.getText();
                String readid1 = readid.getText();
                String chose = "全空";

                boolean flag = true;    //判断是否需要重新输入
                //判断那个输入框为空
                if (readername1.equals("")&&!readid1.equals("")) {
                    chose = "名字为空";
                }else if (!readername1.equals("")&&readid1.equals("")) {
                    chose = "id为空";
                }
                if (flag){
                    ReaderQuery.super.setVisible(false);
                    if (m.executeQuery_reader_query(chose, readername1, readid1))
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
                ReaderQuery.super.setVisible(false);
            }
        });
    }
}
