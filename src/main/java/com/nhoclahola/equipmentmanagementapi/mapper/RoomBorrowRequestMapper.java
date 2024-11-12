package com.nhoclahola.equipmentmanagementapi.mapper;

import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.BorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.response.RoomBorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.entities.BorrowRequest;
import com.nhoclahola.equipmentmanagementapi.entities.RoomBorrowRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = RoomMapper.class) // Roomapper to use toRoomResponse method
public interface RoomBorrowRequestMapper
{
    @Mapping(source = "returned", target = "isReturned")
    RoomBorrowRequestResponse toRoomBorrowRequestResponse(RoomBorrowRequest borrowRequest);

    List<RoomBorrowRequestResponse> toRoomBorrowRequestResponseList(List<RoomBorrowRequest> borrowRequestList);

    default Page<RoomBorrowRequestResponse> toPageRoomBorrowRequestResponse(Page<RoomBorrowRequest> borrowRequestList)
    {
        List<RoomBorrowRequestResponse> content = borrowRequestList.getContent().stream()
                .map(this::toRoomBorrowRequestResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(content, borrowRequestList.getPageable(), borrowRequestList.getTotalElements());
    }
}
