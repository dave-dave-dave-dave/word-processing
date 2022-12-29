package com.lit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IWordCountAnalyzerImpl implements IWordCountAnalyzer {

    @Override
    public int calculateHighestWordCount(String text) {
        String[] words = text.split("\\W+");

//        Map<String, Integer> wordCount = new HashMap<>();
//        for (String word : words) {
//            word = word.toLowerCase();
//            if (wordCount.containsKey(word)) {
//                wordCount.put(word, wordCount.get(word) + 1);
//            } else {
//                wordCount.put(word, 1);
//            }
//        }

        List<IWordCount> iwords = new ArrayList<>();
        for(String word: words){
            IWordCount iword = new IWordCountImpl(word);
            if(iwords.contains(iword)){
                iwords.
            }
        }


        List<Map.Entry<String, Integer>> sortedWords = wordCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .toList();

        System.out.println(sortedWords.get(sortedWords.size()-1).getKey());
        System.out.println(sortedWords.get(sortedWords.size()-1).getValue());
        System.out.println(sortedWords);
        return sortedWords.get(0).getValue();

    }

    @Override
    public int calculateWordCount(String text, String word) {
        return 0;
    }

    @Override
    public List<IWordCount> getMostCountedWords(String text, int top) {
        return null;
    }
}
