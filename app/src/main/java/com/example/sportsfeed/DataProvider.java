package com.example.sportsfeed;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    public static List<NewsItem> getFeaturedMatches() {
        List<NewsItem> list = new ArrayList<>();
        list.add(new NewsItem(1, "Man City vs Arsenal", "An electrifying Premier League clash between two top sides. City edged out a 2-1 victory in a thrilling encounter at the Etihad Stadium.", "Football", R.drawable.img_football1));
        list.add(new NewsItem(2, "Lakers vs Warriors", "LeBron James drops 38 points as the Lakers overcome the Warriors in a classic NBA showdown at Crypto.com Arena.", "Basketball", R.drawable.img_basketball1));
        list.add(new NewsItem(3, "India vs Australia", "India clinches the Test series 2-1 with a dominant batting display in the final innings at the MCG.", "Cricket", R.drawable.img_cricker1));
        list.add(new NewsItem(4, "Federer vs Nadal", "A legendary rematch on centre court. Federer takes the first two sets before Nadal fights back in an epic five-set thriller.", "Tennis", R.drawable.img_tennis1));
        return list;
    }

    public static List<NewsItem> getAllNews() {
        List<NewsItem> list = new ArrayList<>();
        list.add(new NewsItem(5, "Premier League Title Race Heats Up", "With just six games to go, three clubs remain within touching distance of the Premier League title, setting up a thrilling run-in.", "Football", R.drawable.img_football2));
        list.add(new NewsItem(6, "NBA Playoffs: Who Will Make It?", "The battle for the final playoff spots in both conferences is intensifying, with several teams still in contention heading into the final week.", "Basketball", R.drawable.img_basketball2));
        list.add(new NewsItem(7, "Cricket World Cup Squad Announced", "The national selectors have named a 15-man squad for the upcoming Cricket World Cup, with a few surprise inclusions raising eyebrows.", "Cricket", R.drawable.img_cricker2));
        list.add(new NewsItem(8, "Football Transfer Window: Top Rumours", "Several big-money moves are being linked ahead of the summer transfer window, with strikers at a premium across Europe.", "Football", R.drawable.img_football3));
        list.add(new NewsItem(9, "Rookie Sensation Lights Up the Court", "The 19-year-old guard has taken the NBA by storm, averaging 24 points per game over the last 10 outings.", "Basketball", R.drawable.img_basketball1));
        list.add(new NewsItem(10, "Australia Dominates Day One of Test", "The Australian pace attack wreaked havoc on the opposition, reducing them to 120 all out before stumps on day one.", "Cricket", R.drawable.img_cricker1));
        list.add(new NewsItem(11, "Champions League Semi-Final Preview", "A look at the four remaining clubs vying for glory in this season's UEFA Champions League and what sets each side apart.", "Football", R.drawable.img_football1));
        list.add(new NewsItem(12, "Tennis Star Returns from Injury", "The world number three has been confirmed to return at next month's clay-court tournament after a three-month absence.", "Tennis", R.drawable.img_tennis1));
        return list;
    }

    public static List<NewsItem> getRelatedStories(String category, int excludeId) {
        List<NewsItem> all = new ArrayList<>(getFeaturedMatches());
        all.addAll(getAllNews());
        List<NewsItem> related = new ArrayList<>();
        for (NewsItem item : all) {
            if (item.getCategory().equals(category) && item.getId() != excludeId) {
                related.add(item);
                if (related.size() == 3) break;
            }
        }
        return related;
    }
}