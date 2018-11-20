package com.example.nextnote.group;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupController {

	private final GroupRepository groupRepository;

	/**
	 * Spring will automatically inject the groupRepository
	 *
	 * @param groupRepository
	 */
	@Autowired
	public GroupController(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	/**
	 * Returns all our groups from the database
	 *
	 * @return
	 */
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public List<Group> all() {
		return this.groupRepository.findAll();
	}

	/**
	 * Return the group with the specified id or null if is not available
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
	public Group one(@PathVariable("id") Long id) {
		return this.groupRepository.findById(id).orElse(null);
	}

	/**
	 * Create a new group
	 *
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "/groups", method = RequestMethod.POST)
	public Group create(@RequestBody Group group) {
		group.setId(null); // There should not be an id when creating a new record
		this.groupRepository.save(group);
		return group;
	}

	/**
	 * Update the group with the given id
	 *
	 * @param id
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "/groups/{id}", method = RequestMethod.PUT)
	public Group update(@PathVariable("id") Long id, @RequestBody Group group) {
		// Retrieve the group by the id
		Optional<Group> optionalGroup = this.groupRepository.findById(id);
		if (optionalGroup.isPresent()) {
			Group dbGroup = optionalGroup.get();
			dbGroup.setName(group.getName());
			this.groupRepository.save(dbGroup);
			return dbGroup;
		}

		return null;
	}

}