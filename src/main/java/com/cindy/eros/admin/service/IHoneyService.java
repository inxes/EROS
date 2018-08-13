package com.cindy.eros.admin.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qcloud.image.ImageClient;

import java.util.List;
import java.util.Map;

/**
 * @program: eros
 * @description:
 * @author: cindy
 * @create: 2018-08-13 16:24
 **/

public interface IHoneyService {

    public String uploadImg(ImageClient imageClient, String bucketName, String path);

    public List getSegmente(JSONObject object);

    public List sort(Map map);

    public String allString(JSONArray array);

    public Map segement(String allString);

    public List selectRes(List list);
}
