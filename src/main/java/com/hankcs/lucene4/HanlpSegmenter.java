//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hankcs.lucene4;

import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.utility.Predefine;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public final class HanlpSegmenter {
    private Reader input;
    private AnalyzeContext context;
    private Segment segment;
    private int readNum;

    public HanlpSegmenter(Reader input, Segment segment) {
        this.input = input;
        this.segment = segment;
        this.init();
    }

    private void init() {
        this.context = new AnalyzeContext();
    }

    public synchronized Term next() {
        try {
            Term term = null;

            while((term = this.context.getNextTerm()) == null) {
                int available = this.context.fillBuffer(this.input);
                if (available <= 0) {
                    this.context.reset();
                    return null;
                }else {

                    List<Term> lists = this.segment.seg(String.valueOf(this.context.getSegmentBuff()));
                    Iterator var4 = lists.iterator();

                    while (var4.hasNext()) {
                        Term t = (Term) var4.next();
                        this.context.addToResults(t);
//                        Predefine.logger.severe("hanlp分词：" + t);
                    }

//                    ++this.readNum;
                    this.readNum++;
                    if (this.context.needRefillBuffer()) {
                        break;
                    }
                }
                this.context.bufferConsumed();
                this.context.markBufferOffset();
            }

            if (term != null) {
                term.offset += 4096 * (this.readNum - 1);
            }

            return term;
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public synchronized void reset(Reader input) {
        this.input = input;
        this.readNum = 0;
        this.context.reset();
    }

    public static void main(String[] args) {
        String x = "asdasdaasdasdaasdasdaasdasdaasdasdaasdasdaasdasdaasdasdaasdasdaasdasdasd";
        if (x.length() > 38) {
            System.out.println(x.substring(0, 38));
        } else {
            System.out.println(x);
        }

    }
}
