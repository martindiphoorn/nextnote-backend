package com.example.nextnote.note;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Mappings({
                      @Mapping(target = "id", ignore = true)
              })
    void toEntity(NoteDto noteDto, @MappingTarget Note note);

    NoteDto toDto(Note note);

    List<NoteDto> toDto(List<Note> note);

}
