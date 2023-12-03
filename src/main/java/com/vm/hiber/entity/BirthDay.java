package com.vm.hiber.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record BirthDay(LocalDate birthDay) {
    public long getAge() {
        return ChronoUnit.YEARS.between(birthDay, LocalDate.now());
    }
}
