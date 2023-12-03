package com.vm.hiber.util;

import com.vm.hiber.converter.BirthDayConverter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(new BirthDayConverter());
        return configuration.buildSessionFactory();
    }
}
