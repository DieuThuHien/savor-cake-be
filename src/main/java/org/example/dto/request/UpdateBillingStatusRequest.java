package org.example.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Getter
@Setter
public class UpdateBillingStatusRequest {
    @JsonProperty("billID")
    private String billID;
    private String status;
}
