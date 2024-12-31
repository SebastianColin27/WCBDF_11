package com.upiiz.securityDB.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="roles")
@Builder
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rol_name")
    @Enumerated(EnumType.STRING)
    private RolEnum rolEnum;

    @ManyToMany
    @JoinTable(name="rol_permission", joinColumns = @JoinColumn(name="rol_id"), inverseJoinColumns = @JoinColumn(name="permission_id"))
    private Set<PermissionEntity> permissions;


}
