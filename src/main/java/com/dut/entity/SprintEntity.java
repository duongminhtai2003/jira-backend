package com.dut.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sprint")
public class SprintEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "finish_time")
    private Date finishTime;

    @Column(name = "total_story_point")
    private Integer totalStoryPoint;

    @Column(name = "epic_id")
    private Integer epicId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "entity_type")
    private Integer entityType;

}
