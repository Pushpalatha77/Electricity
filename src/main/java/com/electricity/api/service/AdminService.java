package com.electricity.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electricity.api.data.AdminRepository;
import com.electricity.api.model.Admin;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public Admin insertAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminRepository.save(admin);

	}

	public List<Admin> getAllAdmins() {
		// TODO Auto-generated method stub
		return adminRepository.findAll();
	}

	public void updateAdminById(Admin admin) {
		adminRepository.save(admin);
		// TODO Auto-generated method stub

	}

	public void deleteAdminById(Admin admin) {
		// TODO Auto-generated method stub
		adminRepository.delete(admin);

	}

}
