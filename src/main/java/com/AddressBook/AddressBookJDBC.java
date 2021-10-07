package com.AddressBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddressBookJDBC {

    private static Connection getConnection() {
        String url = "jdbc:mysql://127.0.0.1:3306/AddressBook";
        String userName = "root";
        String password = "1234";
        Connection connection = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

    public static List<ContactInfo> retrieveData(String sql) {
        ResultSet resultSet = null;
        List<ContactInfo> contactInfoList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ContactInfo contactInfo = new ContactInfo();
                contactInfo.setFirst_name(resultSet.getString("first_name"));
                contactInfo.setLast_name(resultSet.getString("last_name"));
                contactInfo.setAddress(resultSet.getString("address"));
                contactInfo.setCity(resultSet.getString("city"));
                contactInfo.setState(resultSet.getString("state"));
                contactInfo.setZip(resultSet.getInt("zip"));
                contactInfo.setPhone_number(resultSet.getLong("phone_number"));
                contactInfo.setEmail(resultSet.getString("email"));

                contactInfoList.add(contactInfo);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return contactInfoList;
    }

    public static void updateData(String first_name, String email, String sql) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, first_name);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                System.out.println("Query updated");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private static void UpdateTable() {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "alter table contact add column created_date Date not null;";
            int result = statement.executeUpdate(sql);
            if (result > 0) {
                System.out.println("Table updated....");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void insertDateInDateColumn() {
        try (Connection connection = getConnection()) {
            String sql = "update contact set created_date =? where first_name=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "2020-08-08");
            preparedStatement.setString(2, "saran");
            int result = preparedStatement.executeUpdate();
            if (result > 0)
                System.out.println("Table data updated");
            else
                System.out.println("Data is not updated");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static List<ContactInfo> getDataFromParticularPeriod(String sql) {
        ResultSet resultSet = null;
        List<ContactInfo> contactInfoList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                ContactInfo contactInfo = new ContactInfo();
                contactInfo.setFirst_name(resultSet.getString("first_name"));
                contactInfo.setLast_name(resultSet.getString("last_name"));
                contactInfo.setAddress(resultSet.getString("address"));
                contactInfo.setCity(resultSet.getString("city"));
                contactInfo.setState(resultSet.getString("state"));
                contactInfo.setZip(resultSet.getInt("zip"));
                contactInfo.setPhone_number(resultSet.getLong("phone_number"));
                contactInfo.setEmail(resultSet.getString("email"));

                contactInfoList.add(contactInfo);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return contactInfoList;
    }

    public static void getCountOfStateOrCity(String sql, String input) {
        ResultSet resultSet = null;
        Map<String, Integer> contactInfoList = new HashMap<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (input.equalsIgnoreCase("city")) {
                while (resultSet.next()) {
                    ContactInfo contactInfo = new ContactInfo();
                    contactInfo.setCity(resultSet.getString("city"));
                    contactInfo.setCount(resultSet.getInt("count"));
                    contactInfoList.put(contactInfo.getCity(), contactInfo.getCount());
                }
                System.out.println(contactInfoList);
            } else if (input.equalsIgnoreCase("state")) {
                while (resultSet.next()) {
                    ContactInfo contactInfo = new ContactInfo();
                    contactInfo.setState(resultSet.getString("state"));
                    contactInfo.setCount(resultSet.getInt("count"));
                    contactInfoList.put(contactInfo.getState(), contactInfo.getCount());
                }
                System.out.println(contactInfoList);
            } else
                System.out.println("Incorrect input....");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
