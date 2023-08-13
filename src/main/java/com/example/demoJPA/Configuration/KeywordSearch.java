package com.example.demoJPA.Configuration;

import java.util.HashMap;
import java.util.Map;

public class KeywordSearch {
    public static String main(String keyword,String[] articles) {

        if(articles == null)return "No articles found with the given keyword.";
        String bestMatch = findHighestMatchingArticle(articles, keyword);

        if (bestMatch != null) {
            return "The best matching article is:\n" + bestMatch;
        } else {
            return "No articles found with the given keyword.";
        }
    }

    public static String findHighestMatchingArticle(String[] articles, String keyword) {
        int highestMatchCount = 0;
        String bestMatch = null;

        for (String article : articles) {
            int matchCount = countKeywordOccurrence(article, keyword);

            if (matchCount > highestMatchCount) {
                highestMatchCount = matchCount;
                bestMatch = article;
            }
        }

        return bestMatch;
    }

    public static int countKeywordOccurrence(String text, String keyword) {
        int count = 0;
        int index = text.indexOf(keyword);

        while (index != -1) {
            count++;
            index = text.indexOf(keyword, index + 1);
        }

        return count;
    }
}

