package com.nhoclahola.equipmentmanagementapi.dto.room.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomWithStatusResponse
{
    private Long roomId;
    private String roomName;
    private boolean isBorrowed;
}
