package com.example.nextnote.group;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "notegroup")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
}