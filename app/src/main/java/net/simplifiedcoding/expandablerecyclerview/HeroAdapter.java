package net.simplifiedcoding.expandablerecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Created by Belal on 7/15/2017.
 */

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> {


    private List<Hero> heroList;
    private Context context;

    private static int currentPosition = 0;

    public HeroAdapter(List<Hero> heroList, Context context) {
        this.heroList = heroList;
        this.context = context;
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_heroes, parent, false);
        return new HeroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final HeroViewHolder holder, final int position) {
        Hero hero = heroList.get(position);
        holder.textViewName.setText(hero.getName());
        holder.textViewRealName.setText(hero.getRealName());
        holder.textViewTeam.setText(hero.getTeam());
        holder.textViewFirstAppearance.setText(hero.getFirstAppearance());
        holder.textViewCreatedBy.setText(hero.getCreatedBy());
        holder.textViewPublisher.setText(hero.getPublisher());
        holder.textViewBio.setText(hero.getBio().trim());

        Glide.with(context).load(hero.getImageUrl()).into(holder.imageView);
        holder.linearLayout.setVisibility(View.GONE);

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

            //toggling visibility
            holder.linearLayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewRealName, textViewTeam, textViewFirstAppearance,
                textViewCreatedBy, textViewPublisher, textViewBio;
        ImageView imageView;
        LinearLayout linearLayout;

        HeroViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewRealName = itemView.findViewById(R.id.textViewRealName);
            textViewTeam = itemView.findViewById(R.id.textViewTeam);
            textViewFirstAppearance = itemView.findViewById(R.id.textViewFirstAppearance);
            textViewCreatedBy = itemView.findViewById(R.id.textViewCreatedBy);
            textViewPublisher = itemView.findViewById(R.id.textViewPublisher);
            textViewBio = itemView.findViewById(R.id.textViewBio);
            imageView = itemView.findViewById(R.id.imageView);

            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}

