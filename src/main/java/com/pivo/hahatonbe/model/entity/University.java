package com.pivo.hahatonbe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "university")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class University {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "university", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Group> groups;

    @OneToMany(mappedBy = "university", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Location> locations;

    @OneToMany(mappedBy = "university")
    @JsonIgnore
    private List<User> adminUser;
}
