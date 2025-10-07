package com.example.backend.dev.commons.services.members;

import com.example.backend.dev.commons.services.members.model.MemberModel;
import com.example.backend.dev.commons.services.members.service.MembersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MembersService {

    private final MemberDAO dao;

    @Override
    public MemberModel save(MemberModel model) {
        return dao.save(model);
    }

    @Override
    public Optional<MemberModel> findById(String memberId) {
        return dao.findById(memberId);
    }

    @Override
    public Optional<MemberModel> findByEmail(String email) {
        return dao.findByEmail(email);
    }
}
