package com.tania.zholob.demo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Procedures {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private int price;
    @ManyToMany
    @JoinTable(
            name = "procedures_has_masters",
            joinColumns = @JoinColumn(name = "procedure_id"),
            inverseJoinColumns = @JoinColumn(name ="master_id")
    )
    private List<Masters> mastersList;

}
