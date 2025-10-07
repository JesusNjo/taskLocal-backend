package com.example.backend.dev.commons.services.members.service;

import com.example.backend.dev.commons.services.members.model.MemberModel;

import java.util.Optional;

public interface MembersService {

    MemberModel save(MemberModel model);

    Optional<MemberModel> findById(String memberId);

    Optional<MemberModel> findByEmail(String email);
}
