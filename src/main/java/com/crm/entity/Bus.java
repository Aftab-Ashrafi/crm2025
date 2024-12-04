package com.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

//    @ManyToMany
//    @JoinTable(name = "bus_stops",
//            joinColumns = @JoinColumn(name = "bus_id"),
//            inverseJoinColumns = @JoinColumn(name = "stops_id"))
//    private Set<Stops> stops = new LinkedHashSet<>();

}