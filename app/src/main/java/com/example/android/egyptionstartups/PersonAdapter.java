package com.example.android.egyptionstartups;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView personName;
        private ImageView personImage;
        private Button read;
        PersonViewHolder(View itemView)
        {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            personName = (TextView) itemView.findViewById(R.id.person_name);
            personImage = (ImageView)itemView.findViewById(R.id.person_image);
            read = (Button)itemView.findViewById(R.id.btn_read);
        }

    }
    Context context;
    List<Person>persons;
    private RequestOptions option;
    public PersonAdapter(List<Person>persons, Context context)
    {
        this.persons = persons;
        this.context = context;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story, parent, false);
        PersonViewHolder phv = new PersonViewHolder(v);
        return phv;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.personName.setText(persons.get(position).name);


        final String name = persons.get(position).name;
        final String ceo = persons.get(position).ceo;
        final String site = persons.get(position).site;
        final String personImage = persons.get(position).personImage;
        final String img = persons.get(position).img_url;
        final String story = persons.get(position).story;

        Glide.with(context).load(persons.get(position).personImage).apply(option).into(holder.personImage);

        holder.read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ReadStoryActivity.class);
                i.putExtra("name", name);
                i.putExtra("ceo", ceo);
                i.putExtra("site", site);
                i.putExtra("personImage", personImage);
                i.putExtra("img", img);
                i.putExtra("story", story);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
