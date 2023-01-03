package com.lit.api;

import com.lit.model.IWordCount;

import java.util.List;

public interface IWordCountAnalyzer {

    int calculateHighestWordCount(String text);
    int calculateWordCount(String text, String word);
    List<IWordCount> getMostCountedWords(String text, int top);
}
