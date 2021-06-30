package com.tania.zholob.demo.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToOne
    @JoinColumn(name = "procedure_id")
    private Procedures procedure;

    @Column(name = "payment_status_id")
    @Enumerated(EnumType.ORDINAL)
    private Payment_statuses paymentStatus;

    @Column(name = "performance_statuses_id")
    @Enumerated(EnumType.ORDINAL)
    private Performance_statuses performance_status;


    @OneToOne
    @JoinColumn(name = "time_slot_id")
    private Time_slots timeSlot;

//    @OneToOne(targetEntity = Time_slots.class)
//    @JoinColumn(name = "time_slot_id", referencedColumnName = "id")
//    private Time_slots timeSlot;


}
