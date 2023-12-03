package com.vm.hiber;

import com.vm.hiber.entity.*;
import com.vm.hiber.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;

import java.time.LocalDate;

public class HibernateRunner {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) {
        Company company = Company.builder()
                .name("Mersedes")
                .build();

        User user = User.builder()
                .user_name("4")
                .personalInfo(PersonalInfo.builder()
                        .first_name("Lena")
                        .last_name("Met")
                        .birthDay(new BirthDay(LocalDate.of(1982, 8, 12)))
                        .build())
                .role(Role.USER)
                .company(company)
                .build();
        log.info("User object in transient state: {}", user);

        try {
            SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
//            session.persist(company);
            session.merge(user);
            User user1 = session.get(User.class, 1);

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Exception occurred: ", e);
        }
    }
}
