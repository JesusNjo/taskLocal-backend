package com.example.backend.dev.commons.services.members.model;

import com.example.backend.dev.commons.enums.GenderEnum;
import com.example.backend.dev.commons.enums.StatusEnum;
import com.example.backend.dev.commons.jpa.audit.AuditBaseModel;
import com.example.backend.dev.commons.services.users.model.UserModel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Validated
@Builder
@Getter
@Setter
@Entity
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class MemberModel extends AuditBaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 6738370577215382524L;

    @Id
    @Column(name = "member_id", nullable = false, length = 36)
    private String memberId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_initial")
    private String middleInitial;

    @Column(name = "dob")
    private Date dob;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "nickname", length = 64)
    private String nickname;

    @Column(name = "avatar")
    private String avatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEnum status;

    @Column(name = "external_id")
    private String externalId;

    @Column(name = "main_electronic_address", nullable = false)
    private String mainElectronicAddress;

    @Column(name = "main_mobile_number", length = 24)
    private String mainMobileNumber;

    @Column(name = "preferred_tz_name", length = 128)
    private String preferredTzName;

    @Column(name = "preferred_locale", length = 32)
    private String preferredLocale;

    @Column(name = "parent_id", length = 32)
    private String parentId;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "locked")
    private boolean locked;

    @Column(name = "credentials_expired")
    private boolean credentialsExpired;

    @Column(name = "account_expired")
    private boolean accountExpired;

    @Column(name = "landline_telephone")
    private String landlineTelephone;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "member")
    private List<UserModel> users;

}
