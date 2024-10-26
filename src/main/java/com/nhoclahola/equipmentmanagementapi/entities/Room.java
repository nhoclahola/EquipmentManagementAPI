package com.nhoclahola.equipmentmanagementapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "rooms")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Room
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private String roomName;

    @OneToMany(mappedBy = "room")
    private Set<RoomEquipment> roomEquipments;
}
