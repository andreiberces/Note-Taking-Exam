package com.dre.notetakingapp.service;

import com.dre.notetakingapp.entity.Note;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class NoteService {
    @Getter
    private static List<Note> noteList = new ArrayList<>();
    private static Long idCounter = 0L;

    public static Note getNoteById(Long id){
        for(Note note: noteList){
            if(note.getId().equals(id)){
                return note;
            }
        }
        return null;
    }

    public static Note createNote(Note note){
        note.setId(++idCounter);
        noteList.add(note);
        return note;
    }

    public static Note updateNoteById(Long id, Note note){
        for(int i=0; i<noteList.size(); i++){
            if(noteList.get(i).getId().equals(id)){
                note.setId(id);
                noteList.set(i, note);
                return note;
            }
        }
        return null;
    }

    public static boolean deleteNoteById(Long id){
        return noteList.removeIf(note -> note.getId().equals(id));
    }

}
