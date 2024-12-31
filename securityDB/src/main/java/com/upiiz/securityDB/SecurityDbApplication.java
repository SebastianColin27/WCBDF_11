package com.upiiz.securityDB;

import com.upiiz.securityDB.entities.PermissionEntity;
import com.upiiz.securityDB.entities.RolEntity;
import com.upiiz.securityDB.entities.RolEnum;
import com.upiiz.securityDB.entities.UserEntity;
import com.upiiz.securityDB.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SecurityDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityDbApplication.class, args);
	}

	UserRepository userRepository;
	@Bean
	CommandLineRunner init(){
		return args -> {
//PERMISOS
			PermissionEntity createPermission = PermissionEntity
					.builder()
					.name("CREATE")
					.build();
			PermissionEntity deletePermission = PermissionEntity
					.builder()
					.name("DELETE")
					.build();
			PermissionEntity updatePermission = PermissionEntity
					.builder()
					.name("UPDATE")
					.build();
			PermissionEntity readPermission = PermissionEntity
					.builder()
					.name("READ")
					.build();
			PermissionEntity deployPermission = PermissionEntity
					.builder()
					.name("DEPLOY")
					.build();
//ROLES
			RolEntity adminRol = RolEntity
					.builder()
					.rolEnum(RolEnum.ADMIN)
					.permissions(Set.of(createPermission, deletePermission, updatePermission,readPermission))
					.build();
			RolEntity guestRol = RolEntity
					.builder()
					.rolEnum(RolEnum.GUEST)
					.permissions(Set.of(readPermission))
					.build();
			RolEntity developerRol = RolEntity
					.builder()
					.rolEnum(RolEnum.DEVELOPER)
					.permissions(Set.of(createPermission, deletePermission, updatePermission,readPermission, deployPermission))
					.build();

			//usuarios
			UserEntity Juan = UserEntity
					.builder()
					.username("Juan")
					.password("1234")
					.isEnabled(true)
					.credentialNoExpired(true)
					.accountNoExpired(false)
					.roles(Set.of(developerRol))
					.build();
			UserEntity Anna = UserEntity
					.builder()
					.username("Anna")
					.password("1234")
					.isEnabled(true)
					.credentialNoExpired(true)
					.accountNoExpired(false)
					.roles(Set.of(guestRol))
					.build();
			UserEntity Jose = UserEntity
					.builder()
					.username("Jose")
					.password("1234")
					.isEnabled(true)
					.credentialNoExpired(true)
					.accountNoExpired(false)
					.roles(Set.of(adminRol))
					.build();



			userRepository.saveAll(List.of(Juan,Anna, Jose));
		};
	}
}
