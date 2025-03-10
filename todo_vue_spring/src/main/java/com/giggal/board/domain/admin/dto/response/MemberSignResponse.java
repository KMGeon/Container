package com.giggal.board.domain.admin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignResponse {
    private String memberEmail;
    private String memberName;
    private String subdivisionName;

}
