package com.example.sportsfeed;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private NewsAdapter newsAdapter;
    private List<NewsItem> allNews;
    private String activeFilter = "All";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Featured matches (horizontal)
        RecyclerView featuredRv = view.findViewById(R.id.rv_featured);
        featuredRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        FeaturedAdapter featuredAdapter = new FeaturedAdapter(DataProvider.getFeaturedMatches(), item -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).openDetail(item);
            }
        });
        featuredRv.setAdapter(featuredAdapter);

        // Latest news (vertical)
        RecyclerView newsRv = view.findViewById(R.id.rv_news);
        newsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        allNews = DataProvider.getAllNews();
        newsAdapter = new NewsAdapter(new ArrayList<>(allNews), item -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).openDetail(item);
            }
        });
        newsRv.setAdapter(newsAdapter);

        // Category chips
        ChipGroup chipGroup = view.findViewById(R.id.chip_group_filter);
        String[] categories = {"All", "Football", "Basketball", "Cricket", "Tennis"};
        for (String cat : categories) {
            Chip chip = new Chip(requireContext());
            chip.setText(cat);
            chip.setCheckable(true);
            chip.setChecked(cat.equals("All"));
            chip.setOnClickListener(v -> {
                activeFilter = cat;
                applyFilter();
            });
            chipGroup.addView(chip);
        }

        // Search bar
        EditText searchBar = view.findViewById(R.id.et_search);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { applyFilter(); }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void applyFilter() {
        View view = getView();
        if (view == null) return;
        EditText searchBar = view.findViewById(R.id.et_search);
        String query = searchBar.getText().toString().toLowerCase().trim();

        List<NewsItem> filtered = new ArrayList<>();
        for (NewsItem item : allNews) {
            boolean categoryMatch = activeFilter.equals("All") || item.getCategory().equals(activeFilter);
            boolean searchMatch = query.isEmpty() || item.getTitle().toLowerCase().contains(query);
            if (categoryMatch && searchMatch) filtered.add(item);
        }
        newsAdapter.updateData(filtered);
    }
}