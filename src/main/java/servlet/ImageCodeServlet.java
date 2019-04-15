package servlet;

import util.SecurityCode;
import util.SecurityImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 生成验证码图片
 */
@WebServlet("/ImageCodeServlet")
public class ImageCodeServlet extends HttpServlet {

    private static final long serialVersionUID = 5672906612342629622L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.字符集
        request.setCharacterEncoding("UTF-8");

        // 2.生成验证码
        String code = SecurityCode.getSecurityCode();

        // 3.将验证码设置到 session 作用域，已方便校验
        HttpSession session=request.getSession();
        session.setAttribute("validCode",code);

        // 4.response 不缓存图片
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");

        // 5.设置响应内容的格式
        response.setContentType("image/jpeg");

        // 6.设置失效时间
        response.setDateHeader("Expires",0);

        // 7.据验证码生成图片
        ImageIO.write(SecurityImage.createImage(code),"jpeg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
