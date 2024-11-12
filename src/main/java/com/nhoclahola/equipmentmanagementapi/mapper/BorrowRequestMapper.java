package com.nhoclahola.equipmentmanagementapi.mapper;

import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.BorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.entities.BorrowRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring") // Roomapper to use toRoomResponse method
public interface BorrowRequestMapper
{
    @Mapping(source = "returned", target = "isReturned")
    BorrowRequestResponse toBorrowRequestResponse(BorrowRequest borrowRequest);

    List<BorrowRequestResponse> toBorrowRequestResponseList(List<BorrowRequest> borrowRequestList);

    default Page<BorrowRequestResponse> toPageBorrowRequestResponse(Page<BorrowRequest> borrowRequestList)
    {
        List<BorrowRequestResponse> content = borrowRequestList.getContent().stream()
                .map(this::toBorrowRequestResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(content, borrowRequestList.getPageable(), borrowRequestList.getTotalElements());
    }
}
