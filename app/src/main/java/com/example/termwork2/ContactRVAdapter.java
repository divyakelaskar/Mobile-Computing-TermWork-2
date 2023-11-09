package com.example.termwork2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ContactRVAdapter extends RecyclerView.Adapter<ContactRVAdapter.ViewHolder> {
        ArrayList<ContactModal> contactModalArrayList;
        Context context;
        public ContactRVAdapter(ArrayList<ContactModal> contactModalArrayList, Context context){
            this.contactModalArrayList = contactModalArrayList;
            this.context =context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.contact_rv_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ContactModal modal = contactModalArrayList.get(position);
            holder.contactNameTV.setText(modal.getContactName());
            holder.contactNumTV.setText(modal.getContactNumber());
        }

        @Override
        public int getItemCount() {
            return contactModalArrayList.size();
        }

        public static class ViewHolder extends  RecyclerView.ViewHolder{
            TextView contactNameTV, contactNumTV;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                contactNameTV = itemView.findViewById(R.id.idStudentName);
                contactNumTV = itemView.findViewById(R.id.idStudentNumber);
            }
        }
    }
