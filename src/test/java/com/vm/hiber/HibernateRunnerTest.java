package com.vm.hiber;

import com.vm.hiber.entity.*;
import com.vm.hiber.util.HibernateUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class HibernateRunnerTest {
    @Test
    public void addNewUserAndCompany() {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Company company = Company.builder()
                .name("Opel")
                .build();

        User user = User.builder()
                .user_name("Max")
                .personalInfo(PersonalInfo.builder()
                        .first_name("Rena")
                        .last_name("Metla")
                        .birthDay(new BirthDay(LocalDate.of(1967, 8, 12)))
                        .build())
                .role(Role.USER)
                .company(company)
                .build();
        company.addUser(user);
        session.persist(user);;
        session.getTransaction().commit();
    }

    @Test
    public void oneToMany() {
        @Cleanup
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Company company = session.get(Company.class, 1);
        System.out.println(company.getUserList());

        session.getTransaction().commit();
    }


//    @Test
//    public void testHibernateApi() throws SQLException, IllegalAccessException {
//        User user;
//        user = User.builder()
//                .user_name("lena@tgl.by")
//                .first_name("Lena")
//                .last_name("Marchuk")
//                .birth_date(LocalDate.of(1982, 07, 02))
//                .age(46)
//                .build();
//        var sql = """
//                insert into
//                %s
//                (%s)
//                values
//                (%s)
//                """;
//        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
//                .map(table -> table.schema() + "." + table.name())
//                .orElse(user.getClass().getName());
//
//        Field[] fields = user.getClass().getDeclaredFields();
//        var columnsName = Arrays.stream(fields).map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
//                        .map(Column::name)
//                        .orElse(field.getName()))
//                .collect(Collectors.joining(", "));
//
//        var columnValues = Arrays.stream(fields).map(field -> "?").collect(Collectors.joining(", "));
//
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java_db", "postgres", "1111");
//        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, columnsName, columnValues));
//
//        for (int i = 0; i < fields.length; i++) {
//            fields[i].setAccessible(true);
//            preparedStatement.setObject(i + 1, fields[i].get(user));
//        }
//        preparedStatement.executeUpdate();
//        System.out.println(preparedStatement);
//
//        preparedStatement.close();
//        connection.close();
//    }
}

