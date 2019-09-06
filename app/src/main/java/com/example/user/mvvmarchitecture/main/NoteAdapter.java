package com.example.user.mvvmarchitecture.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.model.Note;
import com.example.user.mvvmarchitecture.R;
import com.example.user.mvvmarchitecture.databinding.ListItemNoteBinding;

import java.util.List;


/**
 * Created by User on 3/27/2019.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    Context context;
    List<Note> listNote;

    public NoteAdapter(Context context, List<Note> listNote) {
        this.context = context;
        this.listNote = listNote;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ListItemNoteBinding binding;
        public ViewHolder(ListItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemNoteBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_item_note, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        Note note = listNote.get(position);
        holder.binding.setModel(note);
    }

    @Override
    public int getItemCount() {
        return listNote.size();
    }
}
