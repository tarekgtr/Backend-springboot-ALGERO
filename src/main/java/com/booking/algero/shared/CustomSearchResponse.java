package com.booking.algero.shared;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomSearchResponse {
    private Long wilayaId;
    private Long roomCategoryId;
    private String searchText;
}
