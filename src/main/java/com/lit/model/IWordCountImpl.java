package com.lit.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class IWordCountImpl implements IWordCount{

    @EqualsAndHashCode.Include
    private String word;
    private int count;

    public IWordCountImpl(String word) {
        this.word = word.toLowerCase();
        this.count = 1;
    }

    @Override
    public void increaseCount(){
        this.count++;
    }
}
