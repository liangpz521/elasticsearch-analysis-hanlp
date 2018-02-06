//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hankcs.lucene4;

import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

public class HanLPTokenizer extends Tokenizer {
    private final CharTermAttribute termAtt;
    private final OffsetAttribute offsetAtt;
    private final TypeAttribute typeAtt;
    private PositionIncrementAttribute posIncrAtt;
    private Set<String> filter;
    private boolean enablePorterStemming;
    private HanlpSegmenter hanlpSegmenter;
    private int position;

    public HanLPTokenizer(Segment segment, Set<String> filter, boolean enablePorterStemming) {
        this.filter = filter;
        this.enablePorterStemming = enablePorterStemming;
        this.offsetAtt = (OffsetAttribute)this.addAttribute(OffsetAttribute.class);
        this.termAtt = (CharTermAttribute)this.addAttribute(CharTermAttribute.class);
        this.typeAtt = (TypeAttribute)this.addAttribute(TypeAttribute.class);
        this.posIncrAtt = (PositionIncrementAttribute)this.addAttribute(PositionIncrementAttribute.class);
        this.hanlpSegmenter = new HanlpSegmenter(this.input, segment);
    }

    public final boolean incrementToken() throws IOException {
        this.clearAttributes();
        this.position = 0;
        Term term = this.hanlpSegmenter.next();
        if (term != null) {
            this.posIncrAtt.setPositionIncrement(this.position + 1);
            this.termAtt.setEmpty().append(term.word.toLowerCase());
            this.termAtt.setLength(term.word.length());
            int length = term.word.length();
            this.offsetAtt.setOffset(term.offset, term.offset + length);
            this.typeAtt.setType(term.nature.name());
            return true;
        } else {
            return false;
        }
    }

    public void reset() throws IOException {
        super.reset();
        this.position = 0;
        this.hanlpSegmenter.reset(new BufferedReader(this.input));
    }
}
