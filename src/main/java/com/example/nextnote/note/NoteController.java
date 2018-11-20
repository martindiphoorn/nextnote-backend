package com.example.nextnote.note;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoteController {

	private final NoteRepository noteRepository;

	/**
	 * Spring will automatically inject the noteRepository
	 *
	 * @param noteRepository
	 */
	@Autowired
	public NoteController(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	/**
	 * Returns all our notes from the database
	 *
	 * @return
	 */
	@RequestMapping(value = "/notes", method = RequestMethod.GET)
	public List<Note> all() {
		return this.noteRepository.findAll();
	}

	/**
	 * Return the note with the specified id or null if is not available
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
	public Note one(@PathVariable("id") Long id) {
		return this.noteRepository.findById(id).orElse(null);
	}

	/**
	 * Create a new note
	 *
	 * @param note
	 * @return
	 */
	@RequestMapping(value = "/notes", method = RequestMethod.POST)
	public Note create(@RequestBody Note note) {
		note.setId(null); // There should not be an id when creating a new record
		this.noteRepository.save(note);
		return note;
	}

	/**
	 * Update the note with the given id
	 *
	 * @param id
	 * @param note
	 * @return
	 */
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
	public Note update(@PathVariable("id") Long id, @RequestBody Note note) {
		// Retrieve the note by the id
		Optional<Note> optionalNote = this.noteRepository.findById(id);
		if (optionalNote.isPresent()) {
			Note dbNote = optionalNote.get();
			dbNote.setName(note.getName());
			this.noteRepository.save(dbNote);
			return dbNote;
		}

		return null;
	}

}