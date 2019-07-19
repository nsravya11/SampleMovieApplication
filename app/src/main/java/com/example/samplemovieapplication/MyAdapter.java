package com.example.samplemovieapplication;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Movie> moviesList;
    Context context;
    public MyAdapter(List<Movie> moviesList,Context context) {
        this.moviesList=moviesList;
        this.context=context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item_view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(item_view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SpannableString string = new SpannableString(moviesList.get(position).getName());
        string.setSpan(new UnderlineSpan(), 0, string.length(), 0);
        holder.name.setText(string);
        String language=moviesList.get(position).getLanguage();
        if(language.equals("TEL"))
        {
            language="Telugu";
        }
        else if(language.equals("HIN"))
        {
            language="Hindi";
        }
        holder.lan_rating.setText(language+" | "+moviesList.get(position).getRating());
        holder.lan_rating.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_star_black_24dp,0);
        final int x=position;
        String imageUrl=moviesList.get(position).getIcon();
        Glide.with(context).load(imageUrl).apply(RequestOptions.bitmapTransform(new RoundedCorners(70))).into(holder.iView);
        final String finalLanguage = language;
        final  Intent intent=new Intent(context,Main2Activity.class);
        intent.putExtra("filmname",moviesList.get(x).getName());
        intent.putExtra("image",moviesList.get(x).getIcon());
        intent.putExtra("language", finalLanguage);
        intent.putExtra("genre",moviesList.get(x).getGenre());
        intent.putExtra("rating",moviesList.get(x).getRating());
        intent.putExtra("views",moviesList.get(x).getViews());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(intent);
            }
        });
        holder.iView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iView;
        TextView name,lan_rating;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iView=(ImageView)itemView.findViewById(R.id.iView);
            name=(TextView)itemView.findViewById(R.id.tView1);
            lan_rating=(TextView)itemView.findViewById(R.id.tView2);
        }
    }
}
