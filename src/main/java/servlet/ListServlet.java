package servlet;

import dao.ListDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import pojo.Productimage;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String act = request.getParameter("act");
        PrintWriter out = response.getWriter();
        //轮播
        if("carousel".equals(act)) {
            ListDao listDao = new ListDao();
            List<Productimage> productimageList = listDao.queryCarousel();
            JSONArray json = (JSONArray) JSONSerializer.toJSON(productimageList);
            out.print(json);
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
