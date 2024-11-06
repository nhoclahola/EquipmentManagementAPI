package com.nhoclahola.equipmentmanagementapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "room_borrow_requests")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoomBorrowRequest
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDate requestDate;  // Ngày gửi yêu cầu mượn

    private LocalDate borrowDate;   // Ngày bắt đầu mượn (được xử lý sau khi Approved)

    private LocalDate returnDate;   // Ngày dự kiến trả phòng

    private boolean isReturned;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
