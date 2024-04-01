package com.dre.notetakingapp.controller;

import com.dre.notetakingapp.entity.Note;
import com.dre.notetakingapp.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @GetMapping()
    public ResponseEntity<List<Note>> getAllNotes(){
        List<Note> notes = NoteService.getNoteList();

        if(notes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getNoteById(@PathVariable  Long id){
        Note note = NoteService.getNoteById(id);
        if(note == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
        }else{
            return ResponseEntity.ok(note);
        }
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note){
        Note createdNote = NoteService.createNote(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateNote(@PathVariable Long id, @RequestBody Note note){
        note.setId(id);
        Note updatedNote = NoteService.updateNoteById(id, note);
        if(updatedNote == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
        }else{
            return ResponseEntity.ok(updatedNote);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNoteById(@PathVariable Long id){
        boolean isRemoved = NoteService.deleteNoteById(id);
        if(!isRemoved){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Note deleted successfully");
    }
}
