/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websocket.binary;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat.Encoding;

/**
 * Static utility class containing methods used for websocket encoding
 * and decoding.
 *
 * @author DHRUV CHOPRA
 */
class WebSocketUtils {
    
    static final String SessionAttribute = "isWEB";
    // Construct a successful websocket handshake response using the key param
    // (See RFC 6455).
    static WebSocketHandShakeResponse buildWSHandshakeResponse(String key){
        String response = "HTTP/1.1 101 Web Socket Protocol Handshake\r\n";
        response += "Upgrade: websocket\r\n";
        response += "Connection: Upgrade\r\n";
        response += "Sec-WebSocket-Accept: " + key + "\r\n";
        response += "\r\n";        
        return new WebSocketHandShakeResponse(response);
    }
    
    // Parse the string as a websocket request and return the value from
    // Sec-WebSocket-Key header (See RFC 6455). Return empty string if not found.
    static String getClientWSRequestKey(String WSRequest) {
        String[] headers = WSRequest.split("\r\n");
        String socketKey = "";
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].contains("Sec-WebSocket-Key")) {
                socketKey = (headers[i].split(":")[1]).trim();
                break;
            }
        }
        return socketKey;
    }    
    
    // 
    // Builds the challenge response to be used in WebSocket handshake.
    // First append the challenge with "258EAFA5-E914-47DA-95CA-C5AB0DC85B11" and then
    // make a SHA1 hash and finally Base64 encode it. (See RFC 6455)
    static String getWebSocketKeyChallengeResponse(String challenge) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        challenge += "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        MessageDigest cript = MessageDigest.getInstance("SHA-1");
        cript.reset();
        cript.update(challenge.getBytes("utf8"));
        byte[] hashedVal = cript.digest();        
        return Base64.encodeBytes(hashedVal);
    }
    
    
    
  /// 打包服务器数据
  /// <param name="message">数据</param>
  /// <returns>数据包</returns>
  private static byte[] packData(String message)
  {
      byte[] contentBytes = null;
      byte[] temp = message.getBytes(Charset.forName("UTF-8"));

      if(temp.length < 126)
      {
          contentBytes = new byte[temp.length + 2];
          contentBytes[0] = (byte) 0x81;
          contentBytes[1] = (byte)temp.length;
          System.arraycopy(temp, 0, contentBytes, 2, temp.length);
      }
      else if(temp.length < 0xFFFF)
      {
          contentBytes = new byte[temp.length + 4];
          contentBytes[0] = (byte) 0x81;
          contentBytes[1] = 126;
          contentBytes[2] = (byte)(temp.length & 0xFF);
          contentBytes[3] = (byte)(temp.length >> 8 & 0xFF);
          System.arraycopy(temp, 0, contentBytes, 4, temp.length);
      }
      else
      {
          // 暂不处理超长内容
      }

      return contentBytes;
  }
    
}
