package com.hfad.bitsandpizzas;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class TopFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout)inflater.inflate(R.layout.fragment_top, container, false);

        RecyclerView recyclerView = (RecyclerView)layout.findViewById(R.id.pizza_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        String[] pizzaNames = new String[2];
        for (int i = 0; i < 2; i++) {
            pizzaNames[i] = Pizza.pizzas[i].getName();
        }

        int[] pizzaImgs = new int[2];
        for (int i = 0; i < 2; i++) {
            pizzaImgs[i] = Pizza.pizzas[i].getImageResourceId();
        }

        CaptionedImagesAdapter captionedImagesAdapter = new CaptionedImagesAdapter(pizzaNames, pizzaImgs);
        captionedImagesAdapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PizzaDetailActivity.class);
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZANO, position);
                getActivity().startActivity(intent);
            }
        });
        recyclerView.setAdapter(captionedImagesAdapter);

        return layout;
    }
}
