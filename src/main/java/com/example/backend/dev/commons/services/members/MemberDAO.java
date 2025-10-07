package com.example.backend.dev.commons.services.members;

import com.example.backend.dev.commons.services.members.model.MemberModel;

import java.util.Optional;

interface MemberDAO {
    MemberModel save(MemberModel model);

    Optional<MemberModel> findById(String memberId);

    Optional<MemberModel> findByEmail(String email);
}
