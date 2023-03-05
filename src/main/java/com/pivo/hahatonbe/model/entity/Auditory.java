package com.pivo.hahatonbe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "auditory")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Auditory {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    private Double latitude;
    private String name;
    private String description;
    private Double longitude;
    @ManyToOne
    @JsonIgnore
    private Location location;
    @OneToMany(mappedBy = "auditory")
    @JsonIgnore
    private List<Event> events;
    @OneToMany(mappedBy = "auditory")
    @JsonIgnore
    private List<Comment> comments;
}
