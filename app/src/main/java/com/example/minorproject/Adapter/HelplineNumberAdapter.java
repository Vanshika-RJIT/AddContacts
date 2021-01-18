package com.example.minorproject.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minorproject.R;

public class HelplineNumberAdapter extends RecyclerView.Adapter<HelplineNumberAdapter.ViewHolder> {
    private View view;

    String title[]={"Women Helpline (All India)","Women Helpline Domestic Abuse","Police",
            "National Commission For Women(NCW)","Delhi Commission For Women","Outer Delhi Helpline","Student/Child Helpline","National Human Right Commission","For Sample"};
     String Number[]={"1091","181","100",
             "26944754","23370597","27034874",
             "1098","9810298900","9425120717"};
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.helplinenumberadapter,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView1.setText(title[position]);
        holder.textView2.setText(Number[position]);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.getAdapterPosition()==0){
                       Intent i = new Intent(Intent.ACTION_CALL);
                       i.setData(Uri.parse("tel:"+Number[0]));
                       view.getContext().startActivity(i);
                }
                else if(holder.getAdapterPosition()==1){
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:"+Number[1]));
                    view.getContext().startActivity(i);
                }
                else if(holder.getAdapterPosition()==2){
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:"+Number[2]));
                    view.getContext().startActivity(i);
                }
                else if(holder.getAdapterPosition()==3){
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:"+Number[3]));
                    view.getContext().startActivity(i);
                }
                else if(holder.getAdapterPosition()==4){
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:"+Number[4]));
                    view.getContext().startActivity(i);
                }
                else if(holder.getAdapterPosition()==5){
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:"+Number[5]));
                    view.getContext().startActivity(i);
                }
                else if(holder.getAdapterPosition()==6){
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:"+Number[6]));
                    view.getContext().startActivity(i);
                }
                else if(holder.getAdapterPosition()==7){
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:"+Number[7]));
                    view.getContext().startActivity(i);
                }
                else
                {
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:"+Number[8]));
                    view.getContext().startActivity(i);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1,textView2;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageid);
            textView1 = itemView.findViewById(R.id.textid1);
            textView2 = itemView.findViewById(R.id.textid2);
            cardView=itemView.findViewById(R.id.carditemid);

        }
    }
}
