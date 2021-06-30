package com.tania.zholob.demo.model.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DynamicInsert
public class Time_slots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date_time;

    @OneToOne
    @JoinColumn(name = "master_id")
    private Masters master;

//    @OneToOne(mappedBy = "timeSlot")
//    private Orders order;

//    @OneToOne(mappedBy = "timeSlot",cascade = CascadeType.ALL)
//    private Orders order;

    public String getFormatData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, EEEE: HH:mm");
        return date_time.format(formatter);
    }


}
