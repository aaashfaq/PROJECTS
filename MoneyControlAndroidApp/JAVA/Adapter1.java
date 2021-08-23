package com.example.PennyWise;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import java.util.List;


public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder>{

    private static List<ModelClass> categoryList;


    public Adapter1(List<ModelClass> modelClassList)
    {

        this.categoryList = modelClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int position) {
        int resources= categoryList.get(position).getImageResource();
        String title= categoryList.get(position).getTitle();
        String body= categoryList.get(position).getBody();
        viewholder.setData(resources,title,body);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        private ImageView imageView;
        private TextView title;
        private TextView body;
        View view;
        String answer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.itemLayout);

            imageView=itemView.findViewById(R.id.image_view);
            title=itemView.findViewById(R.id.title);
            body=itemView.findViewById(R.id.body);
            answer=title.getText().toString();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer=title.getText().toString();
                    if (answer == "Lend/Borrow"){
                        Intent i=new Intent(v.getContext(),contacts.class);
                        v.getContext().startActivity(i);
                    }else{
                        Intent i=new Intent(v.getContext(),TransactionClass.class);
                        i.putExtra("title", answer );
                        v.getContext().startActivity(i);
                    }

                }
            });
        }

        private void setData(int resource, String titleText, String bodyText){
            imageView.setImageResource(resource);
            title.setText(titleText);
            body.setText(bodyText);


        }

       /* @Override
        public void onClick(View v) {
           int position=getAdapterPosition();
            Toast.makeText(v.getContext(),categoryList.get(position).getTitle(),Toast.LENGTH_LONG).show();

        }*/
    }


}