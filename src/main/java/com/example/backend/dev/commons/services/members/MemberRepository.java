package com.example.backend.dev.commons.services.members;

import com.example.backend.dev.commons.services.members.model.MemberModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface MemberRepository extends JpaRepository<MemberModel, String> {

    Optional<MemberModel> findByMainElectronicAddress(String email);
}
