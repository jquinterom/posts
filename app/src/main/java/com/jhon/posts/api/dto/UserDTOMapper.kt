package com.jhon.posts.api.dto

import com.jhon.posts.model.User


class UserDTOMapper {
    fun formUserDTOToUserDomain(userDTO: UserDTO): User {
        return User(
            userDTO.id,
            userDTO.name,
            userDTO.username,
            userDTO.email,
            userDTO.phone,
            userDTO.website
        )
    }

    fun fromUserDTOListToUserDomainList(userDTOList: List<UserDTO>): List<User> {
        return userDTOList.map {
            formUserDTOToUserDomain(it)
        }
    }
}