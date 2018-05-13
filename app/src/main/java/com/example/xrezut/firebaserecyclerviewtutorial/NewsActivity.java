package com.example.xrezut.firebaserecyclerviewtutorial;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView objrecycle;
    private DatabaseReference datarefer;
    private FirebaseRecyclerAdapter<News, NewsActivity.Holder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Bundle bundle = getIntent().getExtras();
        String state_selection= bundle.getString("key1");
        String blood_group= bundle.getString("key3");
        String District_selection=bundle.getString("key2");

        setTitle("Cities");
        datarefer = FirebaseDatabase.getInstance().getReference().child("Cities");
        datarefer.keepSynced(true);

       objrecycle =  findViewById(R.id.myRecycleView);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Cities").child(District_selection);
        Query bloodquery = ref.orderByChild(blood_group);

        objrecycle.hasFixedSize();
        objrecycle.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions firerecycleoptions = new FirebaseRecyclerOptions.Builder<News>().setQuery(bloodquery, News.class).build();

        adapter = new FirebaseRecyclerAdapter<News, NewsActivity.Holder>(firerecycleoptions) {
            @Override
            protected void onBindViewHolder(NewsActivity.Holder holder, final int position, final News model) {
                holder.setName(model.getName());
                holder.setAddress(model.getAddress());
                holder.setImage(getBaseContext(), model.getImage());

                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  final String url = model.getUrl();
                        Intent intent = new Intent(getApplicationContext(), MainActivity_maps.class);
                      //  intent.putExtra("id", url);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public NewsActivity.Holder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.news_row, parent, false);

                return new NewsActivity.Holder(view);
            }
        };

        objrecycle.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
       adapter.stopListening();


    }

    public static class Holder extends RecyclerView.ViewHolder{
        View v;
        public Holder(View itemView){
            super(itemView);
            v = itemView;
        }
        public void setName(String title){
            TextView post_title = v.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setAddress(String desc){
            TextView post_desc = v.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }
        public void setImage(Context ctx, String image){
            ImageView post_image =  v.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);
        }
    }
}
