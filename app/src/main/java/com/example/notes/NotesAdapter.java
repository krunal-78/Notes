package com.example.notes;

import android.net.sip.SipSession;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter  extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    private static final List<Notes> notesArrayList = new ArrayList<Notes>();
    private static InotesRVClicked inotesRVClicked = null;
    public NotesAdapter(InotesRVClicked inotesRVClicked){
        NotesAdapter.inotesRVClicked =inotesRVClicked;
    }
    public static class NotesViewHolder extends RecyclerView.ViewHolder{
        private final TextView note;
        private final ImageView deleteButton;
        public NotesViewHolder(View itemView) {
            super(itemView);
            note = (TextView)itemView.findViewById(R.id.note);
            deleteButton = (ImageView)itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inotesRVClicked.OnItemClicked(v,getAdapterPosition(),notesArrayList.get(getAdapterPosition()));
                }
            });
        }

        public ImageView getDeleteButton() {
            return deleteButton;
        }

        public TextView getNote() {
            return note;
        }
    }

    @NonNull
    @Override
    // returns a NotesViewHolder;
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create view which defines the UI of each item in the list;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        NotesViewHolder notesViewHolder = new NotesViewHolder(view);
        return notesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {
        holder.getNote().setText(notesArrayList.get(position).getText().toString());
    }

    @Override
    public int getItemCount() {
        return notesArrayList.size();
    }

    public void updateList(List<Notes> newNotesList){
        notesArrayList.clear();
        notesArrayList.addAll(newNotesList);

        notifyDataSetChanged();
    }

}
