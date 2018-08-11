package com.cindy.eros.admin.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class BaseResponse {

    public static Map<Integer,String> codeMap = new HashMap<Integer, String>();

    static {
        codeMap.put(404,"接口连接失败");
        codeMap.put(415,"接口连接失败！");
        codeMap.put(500,"服务器错误！");
        //1000-1100文件工具
        codeMap.put(1000,"文件上传失败！");
        codeMap.put(1001,"上传文件不能为空！");
        //1100-1200秘密系统
        codeMap.put(1100,"发表失败！");
    }

    private String message = "succeed";

    private int code = 0;

    private Object data = new JSONArray();

    private String servertime;

    private Integer total;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public static BaseResponse success(Object data){
        BaseResponse br = new BaseResponse();
        br.setData(data);
        return br;
    }

    public static BaseResponse failure(Integer code,String msg){
        BaseResponse br = new BaseResponse();
        br.setCode(code);
        br.setMessage(msg);
        return br;
    }

//    public static BaseResponse failure(Exception ex){
//        BaseResponse br = new BaseResponse();
//        br.setCode();
//    }

    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("message","");
        JSONObject dataJSon = new JSONObject();
        dataJSon.put("limit",0);
        json.put("data",dataJSon);
        BaseResponse res = JSON.parseObject(json.toJSONString(),BaseResponse.class);
    }
}
