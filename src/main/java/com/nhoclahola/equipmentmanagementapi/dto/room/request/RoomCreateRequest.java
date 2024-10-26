package com.nhoclahola.equipmentmanagementapi.dto.room.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomCreateRequest
{
    @NotBlank(message = "Room name must not be blank")
    private String roomName;
}
