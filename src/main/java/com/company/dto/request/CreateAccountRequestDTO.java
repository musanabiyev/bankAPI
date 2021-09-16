package com.company.dto.request;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequestDTO extends BaseAccountRequest {

    private String id;

}
