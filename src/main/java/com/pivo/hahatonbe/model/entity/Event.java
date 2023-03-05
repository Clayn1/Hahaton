package com.pivo.hahatonbe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pivo.hahatonbe.model.enums.EventType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity(name = "event")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    @ManyToOne
    private Location location;
    @ManyToOne
    private Auditory auditory;
    @ManyToOne
    @JsonIgnore
    private Schedule schedule;
    private String name;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private EventType type;
    private Boolean isRepeatable;
    private Boolean isRepeatableBy2;
    @Transient
    private String dayOfWeek;
}
