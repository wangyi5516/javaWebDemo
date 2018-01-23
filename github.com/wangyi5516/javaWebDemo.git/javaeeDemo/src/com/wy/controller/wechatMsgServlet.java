package com.wy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wy.utils.MessageUtil;
import com.wy.weixin.NewsMessage;
import com.wy.weixin.MessageBase;
import com.wy.weixin.TextMessage;

/**
 * Servlet implementation class wechatMsgServlet
 */
@WebServlet("/wechatMsg")
public class wechatMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static final String token = "wangyi";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public wechatMsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String resContent = processRequest(request);
		
//		String signature = request.getParameter("signature");
//		String timestamp = request.getParameter("timestamp");
//		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
//		System.out.println("signature:" + signature);
//		System.out.println("timestamp:" + timestamp);
//		System.out.println("nonce:" + nonce);
//		System.out.println("echostr:" + echostr);
//		PrintWriter pw = response.getWriter();
//		
//		String ToUserName = request.getParameter("ToUserName");
//		System.out.println("ToUserName:" + ToUserName);
//		
//		String FromUserName = request.getParameter("FromUserName");
//		System.out.println("FromUserName:" + FromUserName);
//		
//		String Content = request.getParameter("Content");
//		System.out.println("Content:" + Content);
		
		PrintWriter pw = response.getWriter();
		
		Logger.getLogger("").info("response:"+resContent);
		
		if(resContent !=null)
			pw.append(resContent);
		else
			pw.append(echostr);
		pw.flush();
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
     * 处理微信发来的请求
     * @param request
     * @return xml
     */
    public static String processRequest(HttpServletRequest request) {
        // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = "未知的消息类型！";
        try {
            // 调用parseXml方法解析请求消息
            Map requestMap = MessageUtil.parseXml(request);
            // 发送方帐号
            String fromUserName = (String) requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = (String) requestMap.get("ToUserName");
            // 消息类型
            String msgType = (String) requestMap.get("MsgType");  
            
            String Content = (String) requestMap.get("Content");
            
            
            Logger.getLogger("").info("FromUserName:"+fromUserName);
            Logger.getLogger("").info("MsgType:"+msgType);
            // 回复文本消息
//            TextMessage textMessage = new TextMessage();
//            textMessage.setToUserName(fromUserName);
//            textMessage.setFromUserName(toUserName);
//            textMessage.setCreateTime(new Date().getTime());
//            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            
           
 
            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                respContent = "您发送的是文本消息！";
                // 设置文本消息的内容
                TextMessage textMessage = new TextMessage();
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(new Date().getTime());
                textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                textMessage.setContent(Content);               
                // 将文本消息对象转换成xml
                respXml = MessageUtil.messageToXml(textMessage);
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
                Logger.getLogger("").info("图片链接："+requestMap.get("PicUrl"));
             // 设置文本消息的内容
                NewsMessage imageMessage = new NewsMessage();
                imageMessage.setToUserName(fromUserName);
                imageMessage.setFromUserName(toUserName);
                imageMessage.setCreateTime(new Date().getTime());
                imageMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
                imageMessage.setPicUrl((String) requestMap.get("PicUrl"));               
                // 将文本消息对象转换成xml
                respXml = MessageUtil.messageToXml(imageMessage);
            }
            // 语音消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
                respContent = "您发送的是小视频消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = (String) requestMap.get("Event");
                // 关注
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                }
                // 取消关注
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                }
            }
//            Logger.getLogger("").info("respContent:"+respContent);//            
//            Logger.getLogger("").info("Content:"+Content);
            
            // 设置文本消息的内容
            //textMessage.setContent(respContent);//respContent);
            
//            Logger.getLogger("").info("textMessage:"+textMessage.toString());
            // 将文本消息对象转换成xml
            //respXml = MessageUtil.messageToXml(textMessage);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }
	
	

}
