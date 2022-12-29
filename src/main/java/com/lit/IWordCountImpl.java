package com.lit;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IWordCountImpl implements IWordCount{

    private String word;
    private int count;

    public IWordCountImpl(String word) {
        this.word = word.toLowerCase();
    }
}
