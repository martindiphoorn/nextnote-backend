package com.example.nextnote.note;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoteController {

	private final NoteMapper noteMapper;

	private final NoteRepository noteRepository;

	/**
	 * Spring will automatically inject the mapper and the repository
	 *
	 * @param noteMapper
	 * @param noteRepository
	 */
	@Autowired
	public NoteController(NoteMapper noteMapper, NoteRepository noteRepository) {
		this.noteMapper = noteMapper;
		this.noteRepository = noteRepository;
	}

	/**
	 * Returns all our notes from the database
	 *
	 * @return
	 */
	@RequestMapping(value = "/notes", method = RequestMethod.GET)
	public List<NoteDto> all() {
		List<Note> notes = this.noteRepository.findAll();
		return noteMapper.toDto(notes);
	}

	/**
	 * Return the note with the specified id or null if is not available
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
	public NoteDto one(@PathVariable("id") Long id) {
		return this.noteRepository.findById(id).map(noteMapper::toDto).orElse(null);

	}

	/**
	 * Create a new note
	 *
	 * @param noteDto
	 * @return
	 */
	@RequestMapping(value = "/notes", method = RequestMethod.POST)
	public NoteDto create(@RequestBody NoteDto noteDto) {
		Note note = new Note();
		note.setId(null); // There should not be an id when creating a new record
		noteMapper.toEntity(noteDto, note); // The mapper will copy all values from the dto
		this.noteRepository.save(note);
		return noteMapper.toDto(note);
	}

	/**
	 * Update the note with the given id
	 *
	 * @param id
	 * @param noteDto
	 * @return
	 */
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
	public NoteDto update(@PathVariable("id") Long id, @RequestBody NoteDto noteDto) {
		// Retrieve the note by the id
		Optional<Note> optionalNote = this.noteRepository.findById(id);
		if (optionalNote.isPresent()) {
			Note note = optionalNote.get();
			noteMapper.toEntity(noteDto, note);
			this.noteRepository.save(note);
			return noteMapper.toDto(note);
		}

		return null;
	}

}
