package com.onemount.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

import javax.persistence.*;

import antlr.collections.List;

@Data
@NoArgsConstructor
@Entity(name = "Rolestaff")
@Table(name = "Rolestaff")
public class RoleStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Role;
    private String Staff_Id;

    public RoleStaff(String Role, String Staff_Id){
        this.Role = Role;
        this.Staff_Id = Staff_Id;
    }
}
