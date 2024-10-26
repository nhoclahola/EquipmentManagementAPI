package com.nhoclahola.equipmentmanagementapi.dto.room.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomResponse
{
    private Long roomId;
    private String roomName;
}
