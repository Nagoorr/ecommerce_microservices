package com.ecommerce.user.mapper;

import com.ecommerce.user.entity.User;
import com.ecommerce.user.dto.UserDTO;

public class UserMapper {
    public static User toUserEntity(UserDTO dto) {
        User user = new User();
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setMobileNumber(dto.mobileNumber());
        user.setAddress(AddressMapper.toAddressEntity(dto.addressDTO()));
        return user;
    }

    public static UserDTO toUserDTOEntity(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()
                , user.getMobileNumber(), AddressMapper.toAddressDTOEntity(user.getAddress()));
    }


}
