package com.onemount.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "staff")
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;

    private String staffCode;

    private int age;

    private String RoleStaffId;

  
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Staff_id")
    private List<RoleStaff> RoleStaffList = new ArrayList<>();

    public Staff(String fullName, String staffCode, int age, String RoleStaffId){
        this.fullName = fullName;
        this.staffCode = staffCode;
        this.age = age;
        
    }
}
