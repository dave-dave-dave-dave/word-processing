package com.lit.api;

import com.lit.model.IWordCount;
import com.lit.model.IWordCountImpl;
import com.lit.service.TextService;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class IWordCountAnalyzerImpl implements IWordCountAnalyzer {

    private final TextService textService;

    @Override
    public int calculateHighestWordCount(String text) {
        List<IWordCount> iwords = textService.textToWordList(text);
        textService.sortWordListByCountAndThenAlphabetically(iwords);
        return iwords.get(0).getCount();
    }

    @Override
    public int calculateWordCount(String text, String word) {
        List<IWordCount> iwords = textService.textToWordList(text);
        IWordCount iword = new IWordCountImpl(word);
        if (!iwords.contains(iword)) {
            return 0;
        } else {
            return iwords.get(iwords.indexOf(iword)).getCount();
        }
    }

    @Override
    public List<IWordCount> getMostCountedWords(String text, int top) {
        List<IWordCount> iwords = textService.textToWordList(text);
        textService.sortWordListByCountAndThenAlphabetically(iwords);
        if (top > iwords.size()) {
            return iwords;
        } else {
            return iwords.subList(0, top);
        }
    }
}
