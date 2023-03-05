package com.pivo.hahatonbe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pivo.hahatonbe.model.enums.LocationType;
import jakarta.persistence.CascadeType;
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

@Entity(name = "locations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    private String imgUrl;
    private LocationType type;
    private Double latitude;
    private Double longitude;
    private String name;
    private String description;
    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<Comment> comments;
    @ManyToOne
    @JsonIgnore
    private University university;
    @OneToMany(mappedBy = "location", cascade = CascadeType.DETACH)
    @JsonIgnore
    private List<Auditory> auditories;
    @OneToMany(mappedBy = "location", cascade = CascadeType.DETACH)
    @JsonIgnore
    private List<Event> events;
}
