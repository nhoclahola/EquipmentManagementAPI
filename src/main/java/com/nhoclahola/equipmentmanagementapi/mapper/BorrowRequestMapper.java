package com.nhoclahola.equipmentmanagementapi.mapper;

import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.BorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.entities.BorrowRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring") // Roomapper to use toRoomResponse method
public interface BorrowRequestMapper
{
    @Mapping(source = "returned", target = "isReturned")
    BorrowRequestResponse toBorrowRequestResponse(BorrowRequest borrowRequest);

    List<BorrowRequestResponse> toBorrowRequestResponseList(List<BorrowRequest> borrowRequestList);
}
