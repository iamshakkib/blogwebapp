package com.shakkib.bloggingwebapp;

import com.shakkib.bloggingwebapp.config.AppConstants;
import com.shakkib.bloggingwebapp.entities.Role;
import com.shakkib.bloggingwebapp.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@EnableAsync
@SpringBootApplication
public class BloggingwebappApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BloggingwebappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("sakib"));

		try {

			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");

			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("ROLE_NORMAL");

			List<Role> roles = List.of(role, role1);

			List<Role> result = this.roleRepository.saveAll(roles);

			result.forEach(r -> {
				System.out.println(r.getName());
			});

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
