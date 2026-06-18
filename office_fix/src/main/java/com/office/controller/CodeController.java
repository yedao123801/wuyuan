package com.office.controller;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.office.util.*;
import com.office.util.ImgRandomCodeUtils;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import java.io.ByteArrayOutputStream;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 * 
图片验证码生成接口，该接口用来生成一张带有4位随机码的图片
 *
 */
  @CrossOrigin(origins = "*")
@Controller
@RequestMapping("/validCode")
public class CodeController {
	/**
 	* 
		该接口生成验证码的逻辑是先随机生成一个字符串并存入session中，然后通过ImgRandomCodeUtils这个工具将字符串打印成图片，
		然后再将图片返回给前端，前端就能显示这个验证码图片了，当前端输入验证码后，会和存入session的这个验证码做对比进行校验。
 	*
 	*/
	@RequestMapping("code") 
	@ResponseBody
    public Object getCode(HttpServletRequest req, HttpServletResponse resp) throws IOException { 
		ImgRandomCodeUtils rdnu = ImgRandomCodeUtils.Instance();//图片验证码生成器
		// 禁止图像缓存。  
        resp.setHeader("Pragma", "no-cache"); 
        resp.setHeader("Cache-Control", "no-cache"); 
        resp.setDateHeader("Expires", 0); 
        resp.setContentType("image/jpeg"); 
        // 将图像输出到Servlet输出流中。  
     ByteArrayOutputStream  sos = new ByteArrayOutputStream ();
        ImageIO.write(rdnu.getBuffImg(), "jpeg", sos);//返回给前台
          byte[] bytes = Base64.encodeBase64(sos.toByteArray());
        String base64 = new String(bytes);
         String imgUrl = "data:image/jpeg;base64,"+base64;
        sos.close(); 
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();;
        CacheManage.putCache(uuid, new Cache(uuid,rdnu.getString(),1000*120,false));
        Map<String,Object> rs = new HashMap<String,Object>();
        rs.put("codeToken",uuid);
        rs.put("imgUrl",imgUrl);
        return rs;
    }
}
