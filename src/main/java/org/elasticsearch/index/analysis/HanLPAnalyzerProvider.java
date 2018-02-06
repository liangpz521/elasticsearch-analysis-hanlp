//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.elasticsearch.index.analysis;

import com.hankcs.hanlp.HanLP.Config;
import com.hankcs.hanlp.utility.Predefine;
import com.hankcs.lucene4.HanLPIndexAnalyzer;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

public class HanLPAnalyzerProvider extends AbstractIndexAnalyzerProvider<HanLPIndexAnalyzer> {
    private final HanLPIndexAnalyzer analyzer;
    private static String sysPath = String.valueOf(System.getProperties().get("user.dir"));

    @Inject
    public HanLPAnalyzerProvider(IndexSettings indexSettings, Environment env, @Assisted String name, @Assisted Settings settings) {
        super(indexSettings, name, settings);
        Config.Normalization = true;
        Config.ShowTermNature = false;
//        if (sysPath.endsWith("elasticsearch") || sysPath.endsWith("elasticsearch/")) {
//            sysPath = sysPath + "plugins/analysis-hanlp";
//        }
        Predefine.logger.severe("基类路径：" + sysPath);

        Config.CustomDictionaryPath = new String[]{sysPath + "/data/dictionary/custom/CustomDictionary.txt", sysPath + "/data/dictionary/person/nrf.txt", sysPath + "/data/dictionary/custom/我的词典.txt", sysPath + "/data/dictionary/custom/现代汉语补充词库.txt", sysPath + "/data/dictionary/custom/全国地名大全.txt", sysPath + "/data/dictionary/custom/人名词典.txt", sysPath + "/data/dictionary/custom/机构名词典.txt", sysPath + "/data/dictionary/custom/上海地名.txt"};
        Config.CoreDictionaryPath = sysPath + "/data/dictionary/CoreNatureDictionary.txt";
        Config.BiGramDictionaryPath = sysPath + "/data/dictionary/CoreNatureDictionary.ngram.txt";
        Config.CoreStopWordDictionaryPath = sysPath + "/data/dictionary/stopwords.txt";
        Config.CoreSynonymDictionaryDictionaryPath = sysPath + "/data/dictionary/synonym/CoreSynonym.txt";
        Config.PersonDictionaryPath = sysPath + "/data/dictionary/person/nr.txt";
        Config.PersonDictionaryTrPath = sysPath + "/data/dictionary/person/nr.tr.txt";
        Config.CharTablePath = sysPath + "/data/dictionary/other/CharTable.txt";
        Config.CharTypePath = sysPath + "/data/dictionary/other/CharType.bin";
        Config.CRFSegmentModelPath = sysPath + "/data/model/segment/CRFSegmentModel.txt";
        Config.HMMSegmentModelPath = sysPath + "/data/model/segment/HMMSegmentModel.bin";
        Config.PlaceDictionaryPath = sysPath + "/data/dictionary/place/ns.txt";
        Config.PlaceDictionaryTrPath = sysPath + "/data/dictionary/place/ns.tr.txt";
        Config.OrganizationDictionaryPath = sysPath + "/data/dictionary/organization/nt.txt";
        Config.OrganizationDictionaryTrPath = sysPath + "/data/dictionary/organization/nt.tr.txt";
        Config.tcDictionaryRoot = sysPath + "/data/dictionary/tc/";
        Config.SYTDictionaryPath = sysPath + "/data/dictionary/pinyin/SYTDictionary.txt";
        Config.PinyinDictionaryPath = sysPath + "/data/dictionary/pinyin/pinyin.txt";
        Config.TranslatedPersonDictionaryPath = sysPath + "/data/dictionary/person/nrf.txt";
        Config.JapanesePersonDictionaryPath = sysPath + "/data/dictionary/person/nrj.txt";
        Config.WordNatureModelPath = sysPath + "/data/model/dependency/WordNature.txt";
        Config.MaxEntModelPath = sysPath + "/data/model/dependency/MaxEntModel.txt";
        Config.NNParserModelPath = sysPath + "/data/model/dependency/NNParserModel.txt";
        Config.CRFDependencyModelPath = sysPath + "/data/model/dependency/CRFDependencyModelMini.txt";
        this.analyzer = new HanLPIndexAnalyzer(true);
    }

    public static HanLPAnalyzerProvider getIndexAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        return new HanLPAnalyzerProvider(indexSettings, env, name, settings);
    }

    public static HanLPAnalyzerProvider getSmartAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        return new HanLPAnalyzerProvider(indexSettings, env, name, settings);
    }

    public HanLPIndexAnalyzer get() {
        return this.analyzer;
    }
}
