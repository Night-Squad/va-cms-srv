package com.va.auth.models;
// Generated Sep 9, 2019 4:24:42 PM by Hibernate Tools 4.3.1.Final

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_users_id_seq")
	@SequenceGenerator(name = "generator_users_id_seq", sequenceName = "users_id_seq", schema = "public", allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "password", nullable = false)
	private String password;

}
