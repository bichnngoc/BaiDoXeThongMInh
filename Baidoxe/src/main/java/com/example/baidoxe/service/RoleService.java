package com.example.baidoxe.service;

import com.example.baidoxe.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> listRole();
    RoleDTO findRoleId(Integer Id);
    RoleDTO addRole(RoleDTO roleDTO);
}
