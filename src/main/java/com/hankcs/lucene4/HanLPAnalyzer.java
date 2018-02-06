//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hankcs.lucene4;

import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import java.util.Set;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.Analyzer.TokenStreamComponents;

public class HanLPAnalyzer extends Analyzer {
    boolean enablePorterStemming;
    public Set<String> filter;

    public HanLPAnalyzer(Set<String> filter, boolean enablePorterStemming) {
        this.filter = filter;
    }

    public HanLPAnalyzer(boolean enablePorterStemming) {
        this.enablePorterStemming = enablePorterStemming;
    }

    public HanLPAnalyzer() {
    }

    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer = new HanLPTokenizer(StandardTokenizer.SEGMENT.enableOffset(true), this.filter, this.enablePorterStemming);
        return new TokenStreamComponents(tokenizer);
    }
}
