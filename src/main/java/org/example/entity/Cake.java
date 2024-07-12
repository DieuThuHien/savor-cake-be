package org.example.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "cake")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
public class Cake extends AuditingEntity{
    @Id
    String cakeID;
    String cakeName;
    String cakeCode;
    String cakeType;
    String cakeOriginalPrice;
    String cakeSalePrice;
    String cakeDesc;
    String cakeProfileImagePath;
    List<String> additionalImagePath = new ArrayList<>();
    List<String> cakeSize = new ArrayList<>();
}
