package com.nhoclahola.equipmentmanagementapi.dto.borrow_request.request;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BorrowRequestRequest
{
    private Long roomId;
    private Long equipmentId;
    private Long userId;
    private int quantity;
    private LocalDate returnDate;
}
