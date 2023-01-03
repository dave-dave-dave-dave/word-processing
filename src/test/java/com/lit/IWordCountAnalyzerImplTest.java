package com.lit;

import com.lit.api.IWordCountAnalyzerImpl;
import com.lit.model.IWordCount;
import com.lit.model.IWordCountImpl;
import com.lit.service.TextService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IWordCountAnalyzerImplTest {

    @Mock
    private TextService textServiceMock;

    @InjectMocks
    private IWordCountAnalyzerImpl iWordCountAnalyzer;

    String text;
    List<IWordCount> iwordsSorted;

    @BeforeEach
    void setup() {
        text = "alpha bravo bravo charlie.";
        IWordCount alpha = new IWordCountImpl("alpha");
        IWordCount bravo = new IWordCountImpl("bravo");
        IWordCount charlie = new IWordCountImpl("charlie");
        bravo.setCount(2);
        iwordsSorted = Arrays.asList(bravo, alpha, charlie);
    }

    @Test
    void calculateHighestWordCount_returnsHighestCount() {
        //arrange
        when(textServiceMock.textToWordList(text)).thenReturn(iwordsSorted);
        doNothing().when(textServiceMock).sortWordListByCountAndThenAlphabetically(iwordsSorted);

        //act
        int result = iWordCountAnalyzer.calculateHighestWordCount(text);

        //assert
        assertEquals(2, result);
    }

    @ParameterizedTest
    @CsvSource({"notInText, 0", "alpha, 1", "bravo, 2"})
    void calculateWordCount_returnsWordCountOrZeroIfNotPresent(String word, int expectedCount) {
        //arrange
        when(textServiceMock.textToWordList(text)).thenReturn(iwordsSorted);

        //act
        int result = iWordCountAnalyzer.calculateWordCount(text, word);

        //assert
        assertEquals(expectedCount, result);
    }

    @Test
    void getMostCountedWords_returnsSublistOfWords(){
        //arrange
        when(textServiceMock.textToWordList(text)).thenReturn(iwordsSorted);
        doNothing().when(textServiceMock).sortWordListByCountAndThenAlphabetically(iwordsSorted);

        //act
        List<IWordCount> mostCountedWords = iWordCountAnalyzer.getMostCountedWords(text, 2);

        //assert
        assertEquals(2, mostCountedWords.size());
    }

    @Test
    void getMostCountedWords_topValueHigherThanUniqueWords_returnsFullList(){
        //arrange
        when(textServiceMock.textToWordList(text)).thenReturn(iwordsSorted);
        doNothing().when(textServiceMock).sortWordListByCountAndThenAlphabetically(iwordsSorted);

        //act
        List<IWordCount> mostCountedWords = iWordCountAnalyzer.getMostCountedWords(text, 99);

        //assert
        assertEquals(iwordsSorted.size(), mostCountedWords.size());
    }
}