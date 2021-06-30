package com.tania.zholob.demo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Masters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private int rating;

//    @ManyToMany
//    @JoinTable(
//            name = "procedures_has_masters",
//            joinColumns = @JoinColumn(name = "master_id"),
//            inverseJoinColumns = @JoinColumn(name ="procedure_id")
//    )
//    private List<Procedures> procedures;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "master_id")
    private List<Reviews> reviews;


}
