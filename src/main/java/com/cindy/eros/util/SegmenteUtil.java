package com.cindy.eros.util;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: eros
 * @description: 分词器
 * @author: cindy
 * @create: 2018-08-13 17:12
 **/

public class SegmenteUtil {

    public List<Word> segMore(String text){
        Map<String,String> map = new HashMap<>();
        List<Word> words = WordSegmenter.seg(text,SegmentationAlgorithm.MaximumMatching);
        return words;
    }
}
