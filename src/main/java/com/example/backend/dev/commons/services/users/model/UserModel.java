package com.example.backend.dev.commons.services.users.model;

import com.example.backend.dev.commons.enums.RegistrationTypes;
import com.example.backend.dev.commons.enums.StatusEnum;
import com.example.backend.dev.commons.jpa.audit.AuditBaseModel;
import com.example.backend.dev.commons.services.members.model.MemberModel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;

@Validated
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UserModel extends AuditBaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 2719755759150809166L;

    @Id
    @Column(name = "username", nullable = false, length = 255)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private MemberModel member;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEnum status;

    @Enumerated(EnumType.STRING)
    @Column(name = "registration_type_id", nullable = false, length = 16)
    private RegistrationTypes registrationTypeId;

    @Column(name = "default_user", nullable = false)
    private Boolean defaultUser;

    @Column(name = "external_security_id", nullable = false)
    private String externalSecurityId;
}
