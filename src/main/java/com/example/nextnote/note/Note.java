package com.example.nextnote.note;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;

import com.example.nextnote.group.Group;

@Data
@Entity
@Table(name = "note")
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groupId")
	private Group group;

	private String name;
}