package com.example.baidoxe.mapper;

import com.example.baidoxe.dto.RoleDTO;
import com.example.baidoxe.models.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleDTO ToDTO(Role role){
        if (role == null){
            return null;
        }
        return RoleDTO.builder()
                .Id(role.getId())
                .Role_name(role.getRole_name())
                .Status(role.getStatus())
                .build();

    }

    public Role toRole(RoleDTO roleDTO){
        if(roleDTO == null){
            return null;
        }
        Role role = Role.builder()
                .Id(roleDTO.getId())
                .Role_name(roleDTO.getRole_name())
                .build();
        if (roleDTO.getId() == null){
            role.setStatus(1);
        }else {
            role.setStatus(role.getStatus());
        }
        return  role;
    }


}
