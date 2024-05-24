package com.fabhotels.round2.persistence.support;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class AuditableEntity {

    @Column(name = "aud_created_by", nullable = false, updatable = false)
    @CreatedBy
    @Setter
    protected String audCreatedBy;

    @Column(name = "aud_created_date", nullable = false, updatable = false)
    @CreatedDate
    @Setter
    protected LocalDateTime audCreatedDate;

    @Column(name = "aud_last_modified_by", nullable = false)
    @LastModifiedBy
    @Setter
    protected String audLastModifiedBy;

    @Column(name = "aud_last_modified_date", nullable = false)
    @LastModifiedDate
    @Setter
    protected LocalDateTime audLastModifiedDate;
}
