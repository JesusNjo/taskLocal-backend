package com.example.backend.dev.commons.jpa.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditBaseModel extends TemporalAuditBaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = -7382907975052517137L;

    @Column(name = "created_by",  updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "last_update_by")
    @LastModifiedBy
    private String lastUpdateBy;

}
