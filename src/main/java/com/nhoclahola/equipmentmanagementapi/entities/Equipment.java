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
    private String brandName;
    private String description;
    private String imageUrl;

    // When remove a equipment which already has roomEquipment, it will also remove roomEquipment in database
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoomEquipment> roomEquipments;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BorrowRequest> borrowRequests;
}
