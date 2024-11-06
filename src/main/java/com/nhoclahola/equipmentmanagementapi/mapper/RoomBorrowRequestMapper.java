package com.nhoclahola.equipmentmanagementapi.mapper;

import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.BorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.response.RoomBorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.entities.BorrowRequest;
import com.nhoclahola.equipmentmanagementapi.entities.RoomBorrowRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoomMapper.class) // Roomapper to use toRoomResponse method
public interface RoomBorrowRequestMapper
{
    @Mapping(source = "returned", target = "isReturned")
    RoomBorrowRequestResponse toRoomBorrowRequestResponse(RoomBorrowRequest borrowRequest);

    List<RoomBorrowRequestResponse> toRoomBorrowRequestResponseList(List<RoomBorrowRequest> borrowRequestList);

}
