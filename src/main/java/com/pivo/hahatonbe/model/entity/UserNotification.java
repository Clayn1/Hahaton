package com.pivo.hahatonbe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "user_notification")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserNotification {
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    private String subject;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    @JsonIgnore
    private User user;
}
