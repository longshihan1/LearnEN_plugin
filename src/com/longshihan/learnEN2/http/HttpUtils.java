package com.longshihan.learnEN2.http;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {
    private HttpResponceListener listener;

    public HttpUtils(HttpResponceListener listener) {
        this.listener = listener;
    }


    /** GET请求 */
    public  void doGet(String url) {
        ResponceInfo responceInfo=new ResponceInfo();
        InputStream in = null;
        ByteArrayOutputStream baos = null;
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
            // 设置是否向httpUrlConnection输出，post请求，参数要放在http正文内，因此需要设为true,
            // 默认情况下是false;
            httpUrlConnection.setDoOutput(false);
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpUrlConnection.setDoInput(true);
            // 忽略缓存
            httpUrlConnection.setUseCaches(false);
            // 设定请求的方法为"POST"，默认是GET
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.connect();

            // 获得响应状态
            int responseCode = httpUrlConnection.getResponseCode();

            if (HttpURLConnection.HTTP_OK == responseCode) {
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                in = httpUrlConnection.getInputStream();
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }

                responceInfo.setSuccess(true);
                responceInfo.setData(baos.toString("UTF-8"));
                responceInfo.setCode(200);
                if (listener!=null){
                    listener.onGetMessage(responceInfo);
                }
            } else {
                responceInfo.setSuccess(false);
                responceInfo.setMsg("请求异常");
                responceInfo.setCode(responseCode);
                if (listener!=null){
                    listener.onGetMessage(responceInfo);
                }
            }
        } catch (Exception e) {
            responceInfo.setSuccess(false);
            responceInfo.setMsg("请求异常");
            responceInfo.setCode(500);
            if (listener!=null){
                listener.onGetMessage(responceInfo);
            }
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
