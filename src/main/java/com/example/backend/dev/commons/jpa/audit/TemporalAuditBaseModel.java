package com.example.backend.dev.commons.jpa.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TemporalAuditBaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = -2298817698414471339L;

    @Column(name = "created_at",  updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "last_update")
    @LastModifiedDate
    private LocalDateTime lastUpdate;

}
