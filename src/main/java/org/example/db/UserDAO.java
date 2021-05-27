package org.example.db;

import org.example.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final String SELECT_USERS = "SELECT * FROM USERS;";
    private final String SELECT_USER_BY_ID = "SELECT * FROM USERS WHERE id = ?;";
    private final String SELECT_USER_BY_LOGIN = "SELECT * FROM USERS WHERE login = ?;";
    private final String INSERT_USER = "INSERT INTO USERS VALUES(DEFAULT, ?, ?, ?, ?, ?, ?);";
    private final String UPDATE_USER = "UPDATE USERS SET login  = ?, password = ?, firstname = ?, lastname = ?, role_id = ?, status_id = ? WHERE id = ?;";
    private final String DELETE_USER = "DELETE FROM USERS WHERE id = ?;";
    private final String DELETE_USER_BY_LOGIN = "DELETE FROM USERS WHERE login = ?;";

//    public User findUser(Integer id) {
//        User user = null;
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            con = DBCPDataSource.getInstance().getConnection();
//            ps = con.prepareStatement(SELECT_USER_BY_ID);
//            ps.setInt(1, id);
//            rs = ps.getResultSet();
//            if (rs.next()) {
//                user = userMapper(rs);
//            }
//            rs.close();
//            ps.close();
//        } catch (SQLException exception) {
//            DBCPDataSource.getInstance().rollbackAndClose(con);
//        } finally {
//            DBCPDataSource.getInstance().commitAndClose(con);
//        }
//
//        return user;
//    }
//
//    public User findUserByLogin(String login) {
//        User user = null;
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            con = DBCPDataSource.getInstance().getConnection();
//            ps = con.prepareStatement(SELECT_USER_BY_LOGIN);
//            ps.setString(1, login);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                user = userMapper(rs);
//            }
//            rs.close();
//            ps.close();
//        } catch (SQLException exception) {
//            DBCPDataSource.getInstance().rollbackAndClose(con);
//        } finally {
//            DBCPDataSource.getInstance().commitAndClose(con);
//        }
//        return user;
//    }
//
//    public void updateUser(User user) {
//
//    }
//
//    public User userMapper(ResultSet rs) throws SQLException {
//        return new User(
//                rs.getLong("id"),
//                rs.getString("login"),
//                rs.getString("password"),
//                rs.getString("firstname"),
//                rs.getString("lastname"),
//                rs.getInt("role_id"),
//                rs.getInt("status_id")
//        );
//    }
}
