package com.example.sportsfeed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailFragment extends Fragment {

    private static final String ARG_ITEM = "news_item";

    public static DetailFragment newInstance(NewsItem item) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NewsItem item = (NewsItem) getArguments().getSerializable(ARG_ITEM);
        if (item == null) return;

        ImageView imageView = view.findViewById(R.id.iv_detail_image);
        TextView titleView = view.findViewById(R.id.tv_detail_title);
        TextView categoryView = view.findViewById(R.id.tv_detail_category);
        TextView descView = view.findViewById(R.id.tv_detail_description);
        FloatingActionButton fabBookmark = view.findViewById(R.id.fab_bookmark);
        RecyclerView relatedRv = view.findViewById(R.id.rv_related);

        imageView.setImageResource(item.getImageResId());
        titleView.setText(item.getTitle());
        categoryView.setText(item.getCategory());
        descView.setText(item.getDescription());

        // Bookmark button
        updateBookmarkIcon(fabBookmark, item.getId());
        fabBookmark.setOnClickListener(v -> {
            if (BookmarkManager.isBookmarked(requireContext(), item.getId())) {
                BookmarkManager.removeBookmark(requireContext(), item.getId());
                Toast.makeText(getContext(), "Bookmark removed", Toast.LENGTH_SHORT).show();
            } else {
                BookmarkManager.saveBookmark(requireContext(), item);
                Toast.makeText(getContext(), "Bookmarked!", Toast.LENGTH_SHORT).show();
            }
            updateBookmarkIcon(fabBookmark, item.getId());
        });

        // Related stories
        relatedRv.setLayoutManager(new LinearLayoutManager(getContext()));
        NewsAdapter relatedAdapter = new NewsAdapter(
                DataProvider.getRelatedStories(item.getCategory(), item.getId()),
                relatedItem -> {
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).openDetail(relatedItem);
                    }
                }
        );
        relatedRv.setAdapter(relatedAdapter);

        // Back button
        view.findViewById(R.id.btn_back).setOnClickListener(v -> {
            if (getActivity() != null) getActivity().onBackPressed();
        });
    }

    private void updateBookmarkIcon(FloatingActionButton fab, int itemId) {
        if (BookmarkManager.isBookmarked(requireContext(), itemId)) {
            fab.setImageResource(android.R.drawable.btn_star_big_on);
            fab.setColorFilter(
                    getResources().getColor(android.R.color.holo_orange_light, null)
            );
        } else {
            fab.setImageResource(android.R.drawable.btn_star_big_off);
            fab.setColorFilter(
                    getResources().getColor(android.R.color.darker_gray, null)
            );
        }
    }
}