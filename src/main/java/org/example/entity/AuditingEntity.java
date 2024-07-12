package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
@Getter
@Setter
public abstract class AuditingEntity {
    @Field("created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Field("updated_date")
    @LastModifiedDate
    private LocalDateTime updatedDate;
}
