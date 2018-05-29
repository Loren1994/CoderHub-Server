package pers.loren.coderhub.controller;


import com.gexin.fastjson.JSON;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.loren.coderhub.domain.GeTuiBean;
import pers.loren.coderhub.util.Result;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/getui")
public class GeTuiController {

    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "qAZ7zMjpiR6N5bQoGIL4p8";
    private static String appKey = "zWwr2rbf1B9idgvPPIBhJ5";
    private static String masterSecret = "U9ohcH6TtQ67oL4iZbPwU4";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
    private static String clientId = "3f3cba9d7e0b7264717f2439b00ee194";

    public static String getImageStrFromUrl(String imgURL) {
        long startTime = System.currentTimeMillis();
        byte[] data = null;
        try {
            // 创建URL
            URL url = new URL(imgURL);
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            System.out.println("网络耗时:" + (System.currentTimeMillis() - startTime) + "ms  编码开始>>>>>");
            startTime = System.currentTimeMillis();
            data = new byte[inStream.available()];
            inStream.read(data);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        if (data != null) {
            String baseStr = encoder.encode(data);
            System.out.println("base64>>>>>  " + baseStr);
            System.out.println("编码结束 :  耗时" + (System.currentTimeMillis() - startTime) + "ms");
            return baseStr;
        }
        return null;
    }

    @RequestMapping("/test")
    public Result testGT(@RequestParam String picUrl) {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        TransmissionTemplate template = new TransmissionTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        String baseStr = picUrl; //getImageStrFromUrl(picUrl);
        GeTuiBean geTuiBean = new GeTuiBean();
        geTuiBean.setId(1);
        geTuiBean.setMsg("推送内容 - 观测到危险人员");
        geTuiBean.setPic(baseStr);
        String jsonStr = JSON.toJSONString(geTuiBean);
        template.setTransmissionContent(jsonStr);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(clientId);
        //target.setAlias(Alias);
        IPushResult ret;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            System.out.println(">>>>>RequestException");
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(">>>>>>" + ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
        return new Result("推送完毕");
    }
}
