package com.nhoclahola.equipmentmanagementapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrow_requests")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BorrowRequest
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private int quantity;

    private LocalDate requestDate;  // Ngày gửi yêu cầu mượn

    private LocalDate borrowDate;   // Ngày bắt đầu mượn (được xử lý sau khi Approved)

    private LocalDate returnDate;   // Ngày dự kiến trả thiết bị

    private boolean isReturned;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
