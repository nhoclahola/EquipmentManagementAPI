package com.nhoclahola.equipmentmanagementapi.mapper;

import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.BorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.entities.BorrowRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BorrowRequestMapper
{
    BorrowRequestResponse toBorrowRequestResponse(BorrowRequest borrowRequest);

    List<BorrowRequestResponse> toBorrowRequestResponseList(List<BorrowRequest> borrowRequestList);
}
