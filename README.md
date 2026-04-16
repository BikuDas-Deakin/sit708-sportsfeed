# SportsFeed 🏆

A Sports News Feed Android application built as part of SIT708 Task 5.1 at Deakin University.

## Overview

SportsFeed is a single-activity Android app that displays sports news and featured match cards. Users can filter news by category, search for stories, bookmark their favourite articles, and view detailed story pages with related content.

## Features

- **Home Screen**
  - Horizontal RecyclerView for featured match cards
  - Vertical RecyclerView for latest sports news
  - Search bar to filter news by keyword
  - Category filter chips (All, Football, Basketball, Cricket, Tennis)

- **Detail Screen**
  - Large story image
  - Full title, category badge, and description
  - Bookmark / unbookmark with colour-changing star button
  - Related stories RecyclerView at the bottom

- **Bookmarks Screen**
  - Displays all saved stories using SharedPreferences
  - Empty state message when no bookmarks exist
  - Tap any bookmark to reopen the detail view

- **Navigation**
  - Single Activity Architecture
  - Fragment-based navigation with back stack support
  - Bottom Navigation Bar (Home / Bookmarks)

## Tech Stack

| Component | Technology |
|---|---|
| Language | Java |
| UI | XML Layouts, Material Components |
| Navigation | Fragment Manager + Back Stack |
| Data | Hardcoded dummy data (DataProvider) |
| Storage | SharedPreferences (JSON serialisation) |
| Image Loading | Direct resource drawables |
| Min SDK | API 24 |
| Target SDK | API 36 |

## Project Structure
MainActivity.java — Single activity, hosts all fragments
NewsItem.java — Data model (Serializable)
DataProvider.java — Hardcoded news and featured match data
BookmarkManager.java — SharedPreferences bookmark logic
HomeFragment.java — Home screen with search, filter, RecyclerViews
DetailFragment.java — Detail screen with bookmark FAB and related stories
BookmarksFragment.java — Saved bookmarks screen
FeaturedAdapter.java — Adapter for horizontal featured cards
NewsAdapter.java — Adapter for vertical news list and related stories


## How to Run

1. Clone the repository:
```bash
   git clone https://github.com/YOUR_USERNAME/SportsFeed.git](https://github.com/BikuDas-Deakin/sit708-sportsfeed
```
2. Open the project in **Android Studio**
3. Let Gradle sync complete
4. Run on an emulator or physical device with **API 24 or higher**

## Screenshots

| Home Screen | Detail Screen | Bookmarks Screen |
|---|---|---|
| <img width="372" height="806" alt="image" src="https://github.com/user-attachments/assets/7c6f9200-9476-4c14-bdc2-7e133873ec86" />| <img width="372" height="804" alt="image" src="https://github.com/user-attachments/assets/7cc0ba7a-ad07-4655-a1d1-2259f9b5d7c4" />| <img width="362" height="803" alt="image" src="https://github.com/user-attachments/assets/ee8b41dc-1a9e-4063-a29c-d90641cc12bf" /> |

> Replace the placeholders above with actual screenshots before final submission.

## AI Assistance Declaration

This project was completed with limited supplementary assistance from Claude (Anthropic). See the full LLM Declaration Statement in the submission document for details.

## Author

**Biku** — SIT708, Deakin University
