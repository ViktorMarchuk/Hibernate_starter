package com.vm.hiber.converter;

import com.vm.hiber.entity.BirthDay;
import jakarta.persistence.AttributeConverter;

import java.sql.Date;
import java.util.Optional;

public class BirthDayConverter implements AttributeConverter<BirthDay, Date> {
    @Override
    public Date convertToDatabaseColumn(BirthDay birthDay) {
        return Optional.ofNullable(birthDay)
                .map(BirthDay::birthDay)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public BirthDay convertToEntityAttribute(Date date) {
        return Optional.ofNullable(date)
                .map(Date::toLocalDate)
                .map(BirthDay::new)
                .orElse(null);
    }
}
