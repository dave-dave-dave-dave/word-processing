package com.lit;

public class ProcessingApp {

    public static void main(String[] args) {

        IWordCountAnalyzer analyzer = new IWordCountAnalyzerImpl();
        analyzer.calculateHighestWordCount("Groetjes van mij. Ja van mij dus. Van MIJ!\n Ik zeg toch van mij. Niet van jou.\r\n Van mij!");

    }
}
