package com.nhoclahola.equipmentmanagementapi.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "room_equipments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoomEquipment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    private int quantity;
}
