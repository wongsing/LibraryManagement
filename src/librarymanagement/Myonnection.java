package librarymanagement;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Myonnection {

    //连接数据库的属性
    private String DBDriver;
    private String DBURL;
    private String DBUser;
    private String DBPass;
    private Connection conn=null;
    private Statement stmt=null;
    public int is_admin;
    //book表的属性
    public String id;
    public String bookname;
    public String booktype;
    public String author;
    public String translator;
    public String publisher;
    public String publish_time;
    double price;
    int stock ;
    //reader表的属性
    public String readerid;
    public String readername;
    public String readtype;
    public String sex;
    public int max_num;
    public int days_num;
    //borrow表的属性
    public String book_id;
    public String reader_id;
    public String borrow_date;
    public String back_date;
    public int if_back;
    public int borrowed;    //已借数量
    public String is_borrowed = "否";  //是否可以借书
    public boolean borrowed_book = false;      //匹配是否已借
    public String if_back1 = "是";     //是否归还
    public Myonnection(){
        DBDriver="com.mysql.jdbc.Driver";
        DBURL="jdbc:mysql://localhost:3306/librarymanagement";
        DBUser="root";//数据库账户
        DBPass="123456";//数据库密码
        try{
            Class.forName(DBDriver);
            Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
            Statement stmt = conn.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Connection getMyConnection(){
        return conn;
    }
    public Statement getMyStatement(){
        return stmt;
    }
    public void Close(){//关闭数据库连接
        try{
            stmt.close();
            conn.close();
        }catch( SQLException e){
            e.printStackTrace();
        }
    }
    //校验用户名密码是否正确
    public boolean compare(String username , String password){
        boolean flag = false;
        String sql = "select password , is_admin from user where username = \""+username+ "\"";
        try {
            //重新连接数据库
            Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            if (res.next()){
                String pa = res.getString(1);
                if (pa.equals(password)){
                    flag = true;
                    is_admin = res.getInt(2);
                    System.out.println(is_admin);
                }
                else
                    JOptionPane.showMessageDialog(null, "密码错误！");
            }else{
                    JOptionPane.showMessageDialog(null, "用户名不存在");
            }
            res.close();
            conn.close();
            stmt.close();
        } catch (SQLException e ){
            e.printStackTrace();
        }
        return flag;
    }
    //添加图书
    public boolean  executeUpdate_Add(String id , String bookname , String booktype,
     String author,String translator,String publisher,String publish_time,double price , int stock)
    {
        boolean flag = false;
        try {
            //重新连接数据库
            Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
            Statement stmt = conn.createStatement();
            PreparedStatement ps = null;
            String a1 = "INSERT INTO book VALUES(?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(a1);
            ps.setString(1,id);
            ps.setString(2,bookname);
            ps.setString(3,booktype);
            ps.setString(4,author);
            ps.setString(5,translator);
            ps.setString(6,publisher);
            ps.setString(7,publish_time);
            ps.setDouble(8,price);
            ps.setInt(9,stock);
            ps.executeUpdate();
            flag = true;
        }catch (SQLException e ){
            e.printStackTrace();
        }
            return flag;
    }
    //查询图书
    public boolean executeQuery(String id){
        boolean flag = false;
        try {
            //重新连接数据库
            Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
            Statement stmt = conn.createStatement();
            String sql = "select * from book where id = \""+id+ "\"";
            ResultSet res = stmt.executeQuery(sql);
            if (res.next()){
                 bookname = res.getString(2) ;
                 booktype = res.getString(3);
                 author   = res.getString(4);
                 translator= res.getString(5);
                 publisher= res.getString(6);
                 publish_time= res.getString(7);
                 price = res.getDouble(8);
                 stock = res.getInt(9);
                flag = true;
            }else{
                JOptionPane.showMessageDialog(null, "没有此书！");
            }
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return flag;
    }
    //图书信息查询模块
    public boolean executeQuery_book_query(String command,String bookname , String author,String publisher ,String publish_time) {
        boolean flag = false;
        try {
            //重新连接数据库
            int i = 0, j = 0;
            Connection conn = DriverManager.getConnection(DBURL, DBUser, DBPass);
            BookQuery b = new BookQuery();
            PreparedStatement ps = null;
            if (command.equals("书名为空")){
                String sql1 = "select * from book where author =? and publisher = ? and publish_time = ? ";
                ps = conn.prepareStatement(sql1);
                ps.setString(1, author);
                ps.setString(2, publisher);
                ps.setString(3, publish_time);
                ResultSet res = ps.executeQuery();
                while (res.next()) {
                    id = res.getString(1);
                    bookname = res.getString(2);
                    booktype = res.getString(3);
                    author = res.getString(4);
                    translator = res.getString(5);
                    publisher = res.getString(6);
                    publish_time = res.getString(7);
                    price = res.getDouble(8);
                    stock = res.getInt(9);
                    Object[] str = new Object[]{id, bookname, booktype, author, translator, publisher, publish_time, price + "", stock + ""};
                    for (i = 0; i < 9; i++) {
                        b.tbl.setValueAt(str[i], j, i);
                    }
                    j++;
                    flag = true;
                }
            }else if (command.equals("作者为空")){
                String sql2 = "select * from book where bookname =? and publisher = ? and publish_time = ? ";
                ps = conn.prepareStatement(sql2);
                ps.setString(1, bookname);
                ps.setString(2, publisher);
                ps.setString(3, publish_time);
                ResultSet res = ps.executeQuery();
                while (res.next()) {
                    id = res.getString(1);
                    bookname = res.getString(2);
                    booktype = res.getString(3);
                    author = res.getString(4);
                    translator = res.getString(5);
                    publisher = res.getString(6);
                    publish_time = res.getString(7);
                    price = res.getDouble(8);
                    stock = res.getInt(9);
                    Object[] str = new Object[]{id, bookname, booktype, author, translator, publisher, publish_time, price + "", stock + ""};
                    for (i = 0; i < 9; i++) {
                        b.tbl.setValueAt(str[i], j, i);
                    }
                    j++;
                    flag = true;
                }
            }else if (command.equals("出版社为空")){
                String sql3 = "select * from book where bookname =? and author = ? and publish_time = ? ";
                ps = conn.prepareStatement(sql3);
                ps.setString(1, bookname);
                ps.setString(2, author);
                ps.setString(3, publish_time);
                ResultSet res = ps.executeQuery();
                while (res.next()) {
                    id = res.getString(1);
                    bookname = res.getString(2);
                    booktype = res.getString(3);
                    author = res.getString(4);
                    translator = res.getString(5);
                    publisher = res.getString(6);
                    publish_time = res.getString(7);
                    price = res.getDouble(8);
                    stock = res.getInt(9);
                    Object[] str = new Object[]{id, bookname, booktype, author, translator, publisher, publish_time, price + "", stock + ""};
                    for (i = 0; i < 9; i++) {
                        b.tbl.setValueAt(str[i], j, i);
                    }
                    j++;
                    flag = true;
                }
            }else if (command.equals("出版时间为空")){
                String sql4 = "select * from book where bookname =? and author = ? and publisher = ? ";
                ps = conn.prepareStatement(sql4);
                ps.setString(1, bookname);
                ps.setString(2, author);
                ps.setString(3, publisher);
                ResultSet res = ps.executeQuery();
                while (res.next()) {
                    id = res.getString(1);
                    bookname = res.getString(2);
                    booktype = res.getString(3);
                    author = res.getString(4);
                    translator = res.getString(5);
                    publisher = res.getString(6);
                    publish_time = res.getString(7);
                    price = res.getDouble(8);
                    stock = res.getInt(9);
                    Object[] str = new Object[]{id, bookname, booktype, author, translator, publisher, publish_time, price + "", stock + ""};
                    for (i = 0; i < 9; i++) {
                        b.tbl.setValueAt(str[i], j, i);
                    }
                    j++;
                    flag = true;
                }
            }else if (command.equals("全不选")){
                String sql5 = "select * from book ";
                ps = conn.prepareStatement(sql5);
                ResultSet res = ps.executeQuery();
                while (res.next()) {
                    id = res.getString(1);
                    bookname = res.getString(2);
                    booktype = res.getString(3);
                    author = res.getString(4);
                    translator = res.getString(5);
                    publisher = res.getString(6);
                    publish_time = res.getString(7);
                    price = res.getDouble(8);
                    stock = res.getInt(9);
                    Object[] str = new Object[]{id, bookname, booktype, author, translator, publisher, publish_time, price + "", stock + ""};
                    for (i = 0; i < 9; i++) {
                        b.tbl.setValueAt(str[i], j, i);
                    }
                    j++;
                    flag = true;
                }
            }
            if (!flag)
                JOptionPane.showMessageDialog(null, "没有此书！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

        //删除图书
    public boolean executeDelete(String id){
        boolean flag = false;
        try {
                //重新连接数据库
                Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
                Statement stmt = conn.createStatement();
                String sql = "delete from book where id = "+id;
                stmt.executeUpdate(sql);
                flag = true;
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return flag;
    }
    //修改图书
    public boolean  executeUpdate(String id , String bookname , String booktype,
                                      String author,String translator,String publisher,String publish_time,double price , int stock)
    {
        boolean flag = false;
        try {
            //重新连接数据库
            Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
            Statement stmt = conn.createStatement();
            PreparedStatement ps = null;
            String a2 = "UPDATE  book SET id=?,bookname=?, booktype=?,author=?,translator=?,publisher=?, publish_time=?,price=?,stock=? WHERE id = "+id ;
            ps = conn.prepareStatement(a2);
            ps.setString(1,id);
            ps.setString(2,bookname);
            ps.setString(3,booktype);
            ps.setString(4,author);
            ps.setString(5,translator);
            ps.setString(6,publisher);
            ps.setString(7,publish_time);
            ps.setDouble(8,price);
            ps.setInt(9,stock);
            ps.executeUpdate();
            flag = true;
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return flag;
    }
    //修改图书库存
    public boolean executeUpdate_Book_stock(String command , String book_id){
        boolean flag = false;
        try{
            //重新连接数据库
            Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
            Statement stmt = conn.createStatement();
            if (command.equals("借书")){
                String a1 = "UPDATE  book SET  stock=stock-1 WHERE id = "+book_id ;
                stmt.execute(a1);
            }else if(command.equals("还书")){
                String a2 = "UPDATE  book SET  stock=stock+1 WHERE id = "+book_id ;
                stmt.execute(a2);
            }
            flag = true;
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return flag;
    }
    //添加读者信息
    public boolean  executeUpdate_Add_reader(String id , String readername , String readtype, String sex, int max_num,int days_num)
    {
        boolean flag = false;
        try {
            //重新连接数据库
            Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
            Statement stmt = conn.createStatement();
            PreparedStatement ps = null;
            String a1 = "INSERT INTO reader VALUES(?,?,?,?,?,?)";
            ps = conn.prepareStatement(a1);
            ps.setString(1,id);
            ps.setString(2,readername);
            ps.setString(3,readtype);
            ps.setString(4,sex);
            ps.setInt(5,max_num);
            ps.setInt(6,days_num);
            ps.executeUpdate();
            flag = true;
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return flag;
    }
    //查询读者信息
    public boolean executeQuery_reader(String id){
        boolean flag = false;
        try {
            //重新连接数据库
            Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
            Statement stmt = conn.createStatement();
            String sql = "select * from reader where id = \""+id+ "\"";
            ResultSet res = stmt.executeQuery(sql);
            if (res.next()){
                readername = res.getString(2);
                readtype = res.getString(3);
                sex   = res.getString(4);
                max_num= res.getInt(5);
                days_num= res.getInt(6);
                flag = true;
            }else{
                JOptionPane.showMessageDialog(null, "没有此人！");
            }
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return flag;
    }
    //读者信息查询模块
    public boolean executeQuery_reader_query(String command , String readername ,String reader_id) {
        boolean flag = false;
        try {
            //重新连接数据库
            int i = 0, j = 0;
            Connection conn = DriverManager.getConnection(DBURL, DBUser, DBPass);
            ReaderQuery b = new ReaderQuery();
            PreparedStatement ps = null;
            System.out.println(command);
            if (command.equals("名字为空")) {
                String sql1 = "select * from reader where id =? ";
                ps = conn.prepareStatement(sql1);
                ps.setString(1, reader_id);
                ResultSet res = ps.executeQuery();
                while (res.next()) {
                    reader_id = res.getString(1);
                    readername = res.getString(2);
                    readtype = res.getString(3);
                    sex = res.getString(4);
                    max_num = res.getInt(5);
                    days_num = res.getInt(6);
                    Object[] str = new Object[]{reader_id, readername, readtype, sex, max_num, days_num};
                    for (i = 0; i < 6; i++) {
                        b.tbl.setValueAt(str[i], j, i);
                    }
                    j++;
                    flag = true;
                }
            } else if (command.equals("id为空")) {
                String sql2 = "select * from reader where readername =? ";
                ps = conn.prepareStatement(sql2);
                ps.setString(1, readername);
                ResultSet res = ps.executeQuery();
                while (res.next()) {
                    reader_id = res.getString(1);
                    readername = res.getString(2);
                    readtype = res.getString(3);
                    sex = res.getString(4);
                    max_num = res.getInt(5);
                    days_num = res.getInt(6);
                    Object[] str = new Object[]{reader_id, readername, readtype, sex, max_num, days_num};
                    for (i = 0; i < 6; i++) {
                        b.tbl.setValueAt(str[i], j, i);
                    }
                    j++;
                    flag = true;
                }
            } else if (command.equals("全空")){
                String sql3 = "select * from reader ";
                ps = conn.prepareStatement(sql3);
                ResultSet res = ps.executeQuery();
                while (res.next()) {
                    reader_id = res.getString(1);
                    readername = res.getString(2);
                    readtype = res.getString(3);
                    sex = res.getString(4);
                    max_num = res.getInt(5);
                    days_num = res.getInt(6);
                    Object[] str = new Object[]{reader_id, readername, readtype, sex, max_num, days_num};
                    for (i = 0; i < 6; i++) {
                        b.tbl.setValueAt(str[i], j, i);
                    }
                    j++;
                    flag = true;
                }
            }
            if (!flag)
                JOptionPane.showMessageDialog(null, "没有此人！");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return flag;
    }
    //修改读者信息
    public boolean  executeUpdate_reader(String id , String readername , String readtype, String sex, int max_num,int days_num)
    {
        boolean flag = false;
        try {
            //重新连接数据库
            Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
            Statement stmt = conn.createStatement();
            PreparedStatement ps = null;
            String a2 = "UPDATE  reader SET id=?,readername=?, readtype=?,sex=?,max_num=?,days_num=? WHERE id = "+id ;
            ps = conn.prepareStatement(a2);
            ps.setString(1,id);
            ps.setString(2,readername);
            ps.setString(3,readtype);
            ps.setString(4,sex);
            ps.setInt(5,max_num);
            ps.setInt(6,days_num);
            ps.executeUpdate();
            flag = true;
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return flag;
    }

    //删除读者信息
    public boolean executeDelete_reader(String id){
        boolean flag = false;
        try {
            //重新连接数据库
            Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
            Statement stmt = conn.createStatement();
            String sql = "delete from reader where id = "+id;
            stmt.executeUpdate(sql);
            flag = true;
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return flag;
    }

    //修改用户密码
    public boolean executeUpdate_password(String username,String password){
        boolean flag = false;
        try {
            Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
            Statement stmt = conn.createStatement();
            PreparedStatement ps = null;
            String a2 = "UPDATE  user SET password=? WHERE username = "+"\""+username+"\"" ;
            ps = conn.prepareStatement(a2);
            ps.setString(1,password);
            ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //查询借书
    public boolean executeQuery_reader_borrow(String book_id , String reader_id) {
        boolean flag = false;
        try {
            //重新连接数据库,只查询借还书日期，是否归还
            Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
            PreparedStatement ps1 = null;
            String sql2 = "select if_back from borrow where reader_id = ? and book_id=?";
            ps1 = conn.prepareStatement(sql2);
            ps1.setString(1,reader_id);
            ps1.setString(2,book_id);
            ResultSet res2 = ps1.executeQuery();
            if(res2.next()){
                borrowed_book = true;////借了此书
                if_back1 = res2.getString(1);   //借了书是否已还
//                System.out.println("是否已借书"+borrowed_book);
//                System.out.println("是否已还书"+if_back1);
            }else {
                borrowed_book = false;////没借此书
                if_back1 = "是";
            }
            //查询已借几本书，最大借书数
            PreparedStatement ps = null;
            String sql1 = "select count(*)  from borrow where reader_id = ? group by  reader_id";
            ps = conn.prepareStatement(sql1);
            ps.setString(1,reader_id);
            ResultSet res1 = ps.executeQuery();
            if(res1.next()){
                borrowed = res1.getInt(1);
            }
            if (borrowed < max_num)
                is_borrowed = "是";
            flag = true;
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return flag;
    }
    //插入借书记录
    public boolean  executeUpdate_Add_borrow(String book_id , String reader_id , String borrow_date)
    {
        boolean flag = false;
        try {
            //重新连接数据库
            Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
            Statement stmt = conn.createStatement();
            PreparedStatement ps = null;
            //格式化日期实例sdf
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //创建Calendar 对象
            Calendar calendar = Calendar.getInstance();
            //调用add方法给当前日期增加100天
            calendar.add(Calendar.DATE, days_num);
            //返回增加天数后的时间赋值给finalDate
            Date finalDate = calendar.getTime();
            // 打印日期
            String back_date = sdf.format(finalDate );
            String a1 = "INSERT INTO borrow (book_id,reader_id,borrow_date,back_date,if_back) VALUES(?,?,?,?,?)";
            ps = conn.prepareStatement(a1);
            ps.setString(1,book_id);
            ps.setString(2,reader_id);
            ps.setString(3,borrow_date);
            ps.setString(4,back_date);
            ps.setString(5,"否");
            ps.executeUpdate();
            flag = true;
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return flag;
    }

    //删除借书记录，还书
    public boolean executeDelete_borrow(String book_id,String reader_id){
        boolean flag = false;
        try {
            //重新连接数据库
            Connection conn = DriverManager.getConnection(DBURL,DBUser,DBPass);
            PreparedStatement ps1 = null;
            String sql = "delete from borrow where reader_id = ? and book_id=?";
            ps1 = conn.prepareStatement(sql);
            ps1.setString(1,reader_id);
            ps1.setString(2,book_id);
            ps1.executeUpdate();
            flag = true;
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return flag;
    }

    //验证数据库是否连接成功
    public String toString(){
        return "数据库驱动程序"+DBDriver+"，链接地址"+DBURL+"，用户名"+DBUser+"，密码"+DBPass;
    }
}
