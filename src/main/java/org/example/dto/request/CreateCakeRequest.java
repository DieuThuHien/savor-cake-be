package org.example.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
public class CreateCakeRequest {
    @JsonProperty("cakeName")
    String cakeName;
    @JsonProperty("cakeCode")
    String cakeCode;
    @JsonProperty("cakeType")
    String cakeType;
    @JsonProperty("cakeOriginalPrice")
    String cakeOriginalPrice;
    @JsonProperty("cakeSalePrice")
    String cakeSalePrice;
    @JsonProperty("cakeDesc")
    String cakeDesc;
    @JsonProperty("cakeProfileImagePath")
    @NotEmpty(message = "Cake image profile can not be empty")
    String cakeProfileImagePath;
    List<String> additionalImagePath = new ArrayList<>();
    List<String> cakeSize = new ArrayList<>();
}
