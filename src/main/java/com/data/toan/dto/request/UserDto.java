package com.data.toan.dto.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserDto implements Serializable {
    private long id;

    private String username;

}
