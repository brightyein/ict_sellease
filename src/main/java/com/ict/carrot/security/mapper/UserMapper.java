package com.ict.carrot.security.mapper;

import com.ict.carrot.model.User;
import com.ict.carrot.security.dto.AuthenticatedUserDto;
import com.ict.carrot.security.dto.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

// rimmel asghar
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	User convertToUser(RegistrationRequest registrationRequest);

	AuthenticatedUserDto convertToAuthenticatedUserDto(User user);

	User convertToUser(AuthenticatedUserDto authenticatedUserDto);

}
