//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hankcs.lucene4;

import com.hankcs.hanlp.seg.common.Term;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class AnalyzeContext {
    public static final int BUFF_SIZE = 4096;
    private static final int BUFF_EXHAUST_CRITICAL = 100;
    private char[] segmentBuff = new char[4096];
    private int[] charTypes = new int[4096];
    private int buffOffset;
    private int cursor;
    private int available;
    private LinkedList<Term> results = new LinkedList();
    private Set<String> buffLocker = new HashSet();

    public AnalyzeContext() {
    }

    char[] getSegmentBuff() {
        return this.segmentBuff;
    }

    int fillBuffer(Reader reader) throws IOException {
        int readCount = 0;
        if (this.buffOffset == 0) {
            readCount = reader.read(this.segmentBuff);
        } else {
            int offset = this.available - this.cursor;
            if (offset > 0) {
                System.arraycopy(this.segmentBuff, this.cursor, this.segmentBuff, 0, offset);
                readCount = offset;
            }

            readCount += reader.read(this.segmentBuff, offset, 4096 - offset);
        }

        if (readCount < 4096 && readCount > 0) {
            char[] lastSegmentBuff = new char[readCount];
            System.arraycopy(this.segmentBuff, 0, lastSegmentBuff, 0, readCount);
            this.segmentBuff = lastSegmentBuff;
        }

        this.available = readCount;
        this.cursor = 0;
        return readCount;
    }

    boolean isBufferLocked() {
        return this.buffLocker.size() > 0;
    }

    boolean bufferConsumed() {
        return this.cursor == this.available;
    }

    boolean needRefillBuffer() {
        return this.available == 4096 && this.cursor < this.available - 1 && this.cursor > this.available - 100 && !this.isBufferLocked();
    }

    void markBufferOffset() {
        this.buffOffset += this.cursor;
    }

    void reset() {
        this.buffLocker.clear();
        this.available = 0;
        this.buffOffset = 0;
        this.charTypes = new int[4096];
        this.cursor = 0;
        this.segmentBuff = new char[4096];
        this.results.clear();
    }

    Term getNextTerm() {
        return (Term)this.results.pollFirst();
    }

    void addToResults(Term term) {
        this.results.add(term);
    }
}
