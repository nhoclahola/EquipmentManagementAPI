package com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.request;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomBorrowRequestRequest
{
    private Long roomId;
    private LocalDate returnDate;
}
