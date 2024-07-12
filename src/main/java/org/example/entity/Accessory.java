package org.example.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "accessory")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
public class Accessory extends AuditingEntity{
    @Id
    String accessoryID;
    String accessoryName;
    String imagePath;
    BigInteger accessoryPrice;
}
