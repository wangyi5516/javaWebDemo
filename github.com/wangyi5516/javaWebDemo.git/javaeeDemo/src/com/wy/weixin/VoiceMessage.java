package com.wy.weixin;

public class VoiceMessage extends MessageBase{
	
	// 媒体ID  
    private String MediaId;  
    // 语音格式  
    private String Format;
    
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
    
    

}
