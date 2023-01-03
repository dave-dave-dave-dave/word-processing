package com.lit.service;

import com.lit.model.IWordCount;
import com.lit.model.IWordCountImpl;

import java.util.ArrayList;
import java.util.List;

public class TextService {

    public List<IWordCount> textToWordList(String text) {
        String[] words = text.split("\\W+");
        if (words.length == 1) {
            throw new IllegalArgumentException("Input must be a text that can be split into words!");
        }
        List<IWordCount> iwords = new ArrayList<>();
        for (String word : words) {
            IWordCountImpl iword = new IWordCountImpl(word);
            //check if word is already in list. If it is, increment its count, else add it to the list.
            if (iwords.contains(iword)) {
                iwords.get(iwords.indexOf(iword)).increaseCount();
            } else {
                iwords.add(iword);
            }
        }
        return iwords;
    }

    public void sortWordListByCountAndThenAlphabetically(List<IWordCount> iWords) {
        iWords.sort((w1, w2) -> {
            if (w1.getCount() == w2.getCount()) {
                return w1.getWord().compareTo(w2.getWord());
            } else {
                //- because we sort count high to low
                return -Integer.compare(w1.getCount(), w2.getCount());
            }
        });
    }
}
