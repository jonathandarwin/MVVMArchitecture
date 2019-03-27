package com.example.user.mvvmarchitecture.common;

import android.util.Log;

import com.example.user.mvvmarchitecture.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteData {
    public static List<Note> listNote;
    private static boolean init = false;

    public static void getInit(){
        Log.d("masuksiniga", "masuk init lagi");
        if(!init){
            listNote = new ArrayList<>();
            for(int i=0; i<10; i++){
                Note note = new Note();
                note.setId(i);
                note.setTitle("Title " + i);
                note.setDescription("Description " + i);
                listNote.add(note);
            }
            init = true;
        }
    }
}
