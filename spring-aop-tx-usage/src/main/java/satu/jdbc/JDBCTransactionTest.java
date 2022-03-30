package satu.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Author ISJINHAO
 * @Date 2022/2/24 19:41
 */
public class JDBCTransactionTest {

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = DriverManager.getConnection(getProperties("db.url"), getProperties("db.username"), getProperties("db.password"));
            // 设置为true时，会成功插入一条数据，设置为false时，两条都插不进去
            // con.setAutoCommit(true);
            con.setAutoCommit(false);
            statement = con.prepareStatement("insert into t_tx_test(name, remark) values ('zhanjinhao', 'addenda')");
            int i = statement.executeUpdate();
            System.out.println(i > 0 ? "insert success" : "insert failed");

            statement = con.prepareStatement("insert into t_tx_test(name, remark) values ('zhanjinhao1111111111111111', 'addenda')");
            i = statement.executeUpdate();
            System.out.println(i > 0 ? "insert success" : "insert failed");
            con.commit();
        } catch (SQLException e) {
            try {
                assert con != null;
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static String getProperties(String key) {
        Properties properties = new Properties();
        InputStream in = JDBCTransactionTest.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

}
