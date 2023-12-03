package com.vm.hiber.entity;

import com.vm.hiber.converter.BirthDayConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PersonalInfo {
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Convert(converter = BirthDayConverter.class)
    @Column(name = "birth_date")
    private BirthDay birthDay;

}
