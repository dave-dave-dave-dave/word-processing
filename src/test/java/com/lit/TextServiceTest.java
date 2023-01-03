package com.lit;

import com.lit.model.IWordCount;
import com.lit.model.IWordCountImpl;
import com.lit.service.TextService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TextServiceTest {

    private TextService textService;

    @BeforeEach
    void init() {
        this.textService = new TextService();
    }

    private static final String eightWords = "Dit is een zin met acht unieke woorden.";
    private static final String fifteenWordsCases = "Dit is een zin met vijftien woorden. Is komt twee keer voor, ook met hoofdletter.";
    private static final String fifteenWordsCarriageReturn = "Dit is een zin met vijftien woorden." + System.lineSeparator() + " Is komt twee keer voor, ook met hoofdletter.";


    @ParameterizedTest
    @MethodSource("getSentencesAndUniqueWords")
    void textToWordList_returnsListOfUniqueTextWords(String sentence, int uniqueWords) {
        //act
        List<IWordCount> iWordCounts = textService.textToWordList(sentence);
        //assert
        assertEquals(uniqueWords, iWordCounts.size());
    }

    private static Stream<Arguments> getSentencesAndUniqueWords() {
        return Stream.of(
                Arguments.of(eightWords, 8),
                Arguments.of(fifteenWordsCases, 13),
                Arguments.of(fifteenWordsCarriageReturn, 13)
        );
    }

    @Test
    void textToWordList_ignoresCase() {
        //arrange
        IWordCount is = new IWordCountImpl("is");
        //act
        List<IWordCount> iWordCounts = textService.textToWordList(fifteenWordsCases);
        //assert
        assertTrue(iWordCounts.contains(is));
        assertEquals(2, iWordCounts.get(iWordCounts.indexOf(is)).getCount());
    }

    @Test
    void textToWordList_notAText_throwsException(){
        assertThrows(IllegalArgumentException.class, ()-> textService.textToWordList(""));
    }

    @ParameterizedTest
    @MethodSource("getIWordListInDifferentOrders")
    void sortWordListByCountAndThenAlphabetically_isSorted(List<IWordCount> iWords) {
        //act
        textService.sortWordListByCountAndThenAlphabetically(iWords);
        //assert
        assertEquals(5, iWords.get(0).getCount());
        assertEquals("bravo", iWords.get(0).getWord());
        assertEquals("alpha", iWords.get(iWords.size() - 1).getWord());
    }

    private static Iterable<List<IWordCount>> getIWordListInDifferentOrders() {
        IWordCount word1 = new IWordCountImpl("alpha");
        IWordCount word2 = new IWordCountImpl("bravo");
        IWordCount word3 = new IWordCountImpl("charlie");
        IWordCount word4 = new IWordCountImpl("delta");
        word2.setCount(5);
        word3.setCount(5);
        word4.setCount(2);

        return Arrays.asList(
                Arrays.asList(word1, word2, word3, word4),
                Arrays.asList(word4, word3, word2, word1),
                Arrays.asList(word2, word3, word4, word1),
                Arrays.asList(word3, word2, word1, word4)
        );
    }
}