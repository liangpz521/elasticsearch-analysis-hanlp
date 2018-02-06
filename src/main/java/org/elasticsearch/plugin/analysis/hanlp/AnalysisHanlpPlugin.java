//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.elasticsearch.plugin.analysis.hanlp;

import java.util.HashMap;
import java.util.Map;
import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.HanLPAnalyzerProvider;
import org.elasticsearch.index.analysis.HanLPTokenizerFactory;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule.AnalysisProvider;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

public class AnalysisHanlpPlugin extends Plugin implements AnalysisPlugin {
    public static String PLUGIN_NAME = "analysis-hanlp";

    public AnalysisHanlpPlugin() {
    }

    public Map<String, AnalysisProvider<TokenizerFactory>> getTokenizers() {
        Map<String, AnalysisProvider<TokenizerFactory>> extra = new HashMap();
        extra.put("hanlp-index", HanLPTokenizerFactory::getIndexTokenizerFactory);
        extra.put("hanlp-smart", HanLPTokenizerFactory::getSmartTokenizerFactory);
        extra.put("hanlp", HanLPTokenizerFactory::getIndexTokenizerFactory);
        return extra;
    }

    public Map<String, AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        Map<String, AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> extra = new HashMap();
        extra.put("hanlp-index", HanLPAnalyzerProvider::getIndexAnalyzerProvider);
        extra.put("hanlp-smart", HanLPAnalyzerProvider::getSmartAnalyzerProvider);
        extra.put("hanlp", HanLPAnalyzerProvider::getIndexAnalyzerProvider);
        return extra;
    }
}
