package com.ecommerce.user.mapper;

import com.ecommerce.user.entity.Address;
import com.ecommerce.user.dto.AddressDTO;

public class AddressMapper {

    public static AddressDTO toAddressDTOEntity(Address address) {
        return new AddressDTO(address.getId(),address.getStreet(), address.getCity(),
                address.getPincode(), address.getCountry(), address.getState());
    }

    public static Address toAddressEntity(AddressDTO addressDTO) {
        Address address = new Address();
        address.setCity(addressDTO.city());
        address.setCountry(addressDTO.country());
        address.setPincode(addressDTO.pincode());
        address.setState(addressDTO.state());
        address.setStreet(addressDTO.street());
        return address;
    }
}
