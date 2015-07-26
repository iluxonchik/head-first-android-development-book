package com.hfad.bitsandpizzas;

import android.graphics.drawable.Drawable;
import android.provider.Telephony;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CaptionedImagesAdapter  extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    public static interface Listener {
        public void onClick(int position);
    }

    // Each data item in out recycler view is a card, so we need to make our View Holder
    // store card views.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }

    }

    private String[] captions;
    private int[] imageIds;
    private Listener listener;

    public CaptionedImagesAdapter(String[] captions, int[] imageIds) {
        this.captions = captions;
        this.imageIds = imageIds;
    }

    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Tell the recycler which layout to use for each view holder
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return  new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(CaptionedImagesAdapter.ViewHolder holder, final int position) {
        // Create a new view
        // Gets called whenever Recycler View needs to display data in a View Holder

        /*
            Parameters:
                holder -> the view holder that data needs to be bound to
                position -> position in the data set data of the data that needs to be bound
         */

        CardView cv = holder.cardView;
        ImageView imageView = (ImageView)cv.findViewById(R.id.info_image);
        Drawable drawable = cv.getResources().getDrawable(imageIds[position]);
        imageView.setImageDrawable(drawable);

        TextView textView = (TextView)cv.findViewById(R.id.info_text);
        textView.setText(captions[position]);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        // Return the number of items in dataset
        return captions.length;
    }

    public void setListener(Listener l) {
        this.listener = l;
    }
}
