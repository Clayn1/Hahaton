package com.pivo.hahatonbe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    @OneToMany(mappedBy = "schedule")
    private List<Event> events;
    @OneToOne
    @JsonIgnore
    private User user;
    @OneToOne
    @JsonIgnore
    private Group group;
}
