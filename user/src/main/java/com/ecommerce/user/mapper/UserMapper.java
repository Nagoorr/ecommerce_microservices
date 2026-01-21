package com.ecommerce.user.mapper;

import com.ecommerce.user.entity.User;
import com.ecommerce.user.dto.UserDTO;

public class UserMapper {
    public static User toUserEntity(UserDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setMobileNumber(dto.getMobileNumber());
        user.setAddress(AddressMapper.toAddressEntity(dto.getAddressDTO()));
        return user;
    }

    public static UserDTO toUserDTOEntity(User user) {
        return new UserDTO(user.getId().toHexString(), user.getFirstName(), user.getLastName(), user.getEmail()
                , user.getMobileNumber(), AddressMapper.toAddressDTOEntity(user.getAddress()));
    }


}
