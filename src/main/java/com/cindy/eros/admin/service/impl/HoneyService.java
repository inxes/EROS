package com.cindy.eros.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cindy.eros.admin.service.IHoneyService;
import com.cindy.eros.util.SegmenteUtil;
import com.qcloud.image.ImageClient;
import com.qcloud.image.request.GeneralOcrRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-08-13 16:24
 **/

@Service
public class HoneyService implements IHoneyService {

    //腾讯api解析图片内容
    @Override
    public String uploadImg(ImageClient imageClient, String bucketName, String path) {
        String ret;

        //2. 图片内容方式
        GeneralOcrRequest request = new GeneralOcrRequest(bucketName, new File(path));
        ret = imageClient.generalOcr(request);
        return ret;
    }

    //将解析的图片内容集中分词

    @Override
    public List getSegmente(JSONObject object) {

        //实例化分词工具
        JSONArray array = object.getJSONObject("data").getJSONArray("items");
        //返回所有图片文字内容
        String allString = allString(array);
        //返回词频
        Map map = segement(allString);
        //词频倒序排序
        List list = sort(map);
        //选取词频大于1的关键字返回
        List res = selectRes(list);
        return res;
    }

    /**
     * 返回词频
     * @param allString
     * @return
     */
    @Override
    public Map segement(String allString) {
        Map<String,Integer> map = new HashMap<>();
        SegmenteUtil segmenteUtil = new SegmenteUtil();
        List newlist = new ArrayList();
        List segement = segmenteUtil.segMore(allString);
        for (int j = 0;j<segement.size();j++){
            String keyword = segement.get(j).toString();
            if(!newlist.isEmpty() && newlist.contains(keyword)){
                map.put(keyword,map.get(keyword)+1);
            }else{
                newlist.add(keyword);
                map.put(keyword,1);
            }
        }
        return map;
    }


    /**
     * 返回所有图片文字内容
     * @param array
     * @return
     */
    @Override
    public String allString(JSONArray array) {
        String allString = "";
        for (int i = 0;i<array.size();i++){
            JSONObject items = array.getJSONObject(i);
            String itemstring = items.getString("itemstring");
            allString = allString + "," + itemstring;
        }
        return allString;
    }

    //词频倒序排序
    @Override
    public List sort(Map map) {
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        return list;
    }

    /**
     * 排除词频为1的词，排除数字
     * @param list
     * @return
     */
    @Override
    public List selectRes(List list) {
        for (int k = 0;k<list.size();k++){
            String[] str = list.get(k).toString().split("=");
            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
            Matcher isNum = pattern.matcher(str[0]);
            System.out.println(str[1]+"---"+str[0]);
            if(Integer.parseInt(str[1]) < 2){
                System.out.println("remove"+k);
                list.remove(k);
                if(k>0) k--;
            }else if (isNum.matches()){
                System.out.println("remove"+k);
                list.remove(k);
                if(k>0) k--;
            }
        }
        return list;
    }
}
