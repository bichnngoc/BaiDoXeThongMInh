package com.example.baidoxe.service.impl;

import com.example.baidoxe.dto.RoleDTO;
import com.example.baidoxe.mapper.RoleMapper;
import com.example.baidoxe.models.Role;
import com.example.baidoxe.repository.RoleRepository;
import com.example.baidoxe.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleDTO> listRole() {
        List<Role> roles = roleRepository.findActive();
               return roles.stream()
                       .map(roleMapper::ToDTO)
                       .collect(Collectors.toList());
    }

    @Override
    public RoleDTO findRoleId(Integer Id) {
        Optional<Role> roleOptional = roleRepository.findById(Id);
        return roleOptional.map(roleMapper::ToDTO).orElse(null);
    }

    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        Role role = roleMapper.toRole(roleDTO);
        Role savesRole= roleRepository.save(role);
        return roleMapper.ToDTO(savesRole);
    }
}
