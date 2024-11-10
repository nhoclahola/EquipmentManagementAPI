package com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.response;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserInfoRoomBorrowRequestResponse
{
    private Long userId;
    private Long totalBorrowRequest;
    private Long overdueBorrowRequest;
    private LocalDate recentBorrow;
}
