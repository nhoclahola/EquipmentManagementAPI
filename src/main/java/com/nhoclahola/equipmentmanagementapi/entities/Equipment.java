package com.nhoclahola.equipmentmanagementapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "equipments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Equipment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipmentId;
    private String equipmentName;
    private String imageUrl;

    @OneToMany(mappedBy = "equipment")
    private Set<RoomEquipment> roomEquipments;
}
