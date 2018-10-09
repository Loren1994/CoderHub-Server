package pers.loren.coderhub.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

@ServerEndpoint("/loren/{vmcNo}")
@Component
public class WebSocketServer {
    private static CopyOnWriteArrayList<WebSocketServer> sWebSocketServers = new CopyOnWriteArrayList<>();
    private static int onlineCount = 0;
    private Logger logger = java.util.logging.Logger.getLogger("WebSocketServer");
    private Session mSession; // 与客户端连接的会话，用于发送数据
    private String mVmcNo; // 客户端的标识

    /**
     * 对某个机器发送消息
     */
    public static String sendMessage(String message, String vmcNo) {
        boolean success = false;
        for (WebSocketServer server : sWebSocketServers) {
            if (server.mVmcNo.equals(vmcNo)) {
                success = server.sendMessage(message);
                break;
            }
        }
        return success ? message : "failed";
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("vmcNo") String vmcNo) {
        mSession = session;
        sWebSocketServers.add(this);
        mVmcNo = vmcNo;
        addOnlineCount();
        logger.info("设备：" + vmcNo + " 已加入，当前在线人数：" + getOnlineCount());
    }

    @OnClose
    public void onClose(@PathParam("vmcNo") String vmcNo) {
        sWebSocketServers.remove(this);
        subOnlineCount();
        logger.info("设备：" + vmcNo + " 已离线，当前在线人数：" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("-->onMessage " + message);
//        // 让其他客户端都知道消息已收到
//        for (WebSocketServer socketServer : sWebSocketServers) {
//            socketServer.sendMessage("我已经收到你发给我的消息了");
//        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("发生错误");
        error.printStackTrace();
    }

    /**
     * 对外发送消息
     */
    public boolean sendMessage(String message) {
        try {
            mSession.getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.info(e.toString());
            return false;
        }
        return true;
    }

}
