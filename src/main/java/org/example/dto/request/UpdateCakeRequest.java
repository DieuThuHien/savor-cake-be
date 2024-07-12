package org.example.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Getter
@Setter
public class UpdateCakeRequest extends CreateCakeRequest{
    String cakeID;
}
