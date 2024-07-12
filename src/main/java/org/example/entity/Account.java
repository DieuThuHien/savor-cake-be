package org.example.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "account")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
public class Account extends AuditingEntity{
    @Id
    String accountId;
    String name;
    @Indexed(unique = true)
    String phone;
    @Indexed(unique = true)
    String username;
    String password;
    List<String> roles = new ArrayList<>();
}
