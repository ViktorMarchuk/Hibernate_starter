package com.vm.hiber.entity;

import com.vm.hiber.converter.BirthDayConverter;
import lombok.*;

import jakarta.persistence.*;


@Data
@Table(name = "users", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "user_name")
@ToString(exclude = "company")
@Builder
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name")
    private String user_name;
    @Embedded
    private PersonalInfo personalInfo;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}
