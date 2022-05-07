package ru.surantaev.jwt_security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.surantaev.jwt_security.entity.models.Group;
import ru.surantaev.jwt_security.repo.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroup(Group group) {
        return groupRepository.save(group);
    }

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }
}
