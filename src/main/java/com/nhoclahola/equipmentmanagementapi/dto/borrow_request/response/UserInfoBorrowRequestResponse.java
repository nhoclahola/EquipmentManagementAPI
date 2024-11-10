package com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserInfoBorrowRequestResponse
{
    private Long userId;
    private Long totalBorrowRequest;
    private Long overdueBorrowRequest;
    private LocalDate recentBorrow;
}
