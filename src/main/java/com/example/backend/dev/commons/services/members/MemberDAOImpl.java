package com.example.backend.dev.commons.services.members;

import com.example.backend.dev.commons.services.members.model.MemberModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class MemberDAOImpl implements MemberDAO {
    private final MemberRepository repository;
    @Override
    public MemberModel save(MemberModel model) {
        return repository.save(model);
    }

    @Override
    public Optional<MemberModel> findById(String memberId) {
        return repository.findById(memberId);
    }

    @Override
    public Optional<MemberModel> findByEmail(String email) {
        return repository.findByMainElectronicAddress(email);
    }
}
