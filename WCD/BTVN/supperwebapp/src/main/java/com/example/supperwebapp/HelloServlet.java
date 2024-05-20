package com.example.supperwebapp;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    // khởi tạo servlet
    // tạo ra 1 cặp đối tượng duy nhất là request và respone . các request sau đó ới Servlet
    // không cần phải tạo lại cặp request và respospose nữa vì chúng chỉ cần tạo 1 lần đầu tiên
    //duy nhất , điều này xảy do Servlet Container quản lý vòng đời của Servlet
    //vai trò của Servlet Container:
    /*
     *1. LifeCycle Management : Quản lý vòng đời của Servlet , khởi tạo , load class, call method, ... giải phóng
     * 2. JSP support : Quản lý jsp
     * 3. Conmmunication support : Quản lý giao tiếp giữa Servlet và websever
     */

    //Chỉ dc gọi 1 lần trước khi Servlet xử lý các request (trước khi service()  được gọi )
    //Method này có thể ocerride được khởi tạo các resource
    public void init() {
        message = "Hello World!";
    }
    //Service() Được gọi bởi Container khi các client make request , có thể được gọi nhiều lần để xủ lý các request nếu
    //service() dựa vào HTTP Request để gọi các doXXXX() tương ứng
    // Method này được sử lý trong từng Theard ( luồng ) riêng biệt
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    //doPost() được gọi bởi service() tùy thuộc vào HTTP request(method = POST )
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    //doGet() được gọi bởi service() tùy thuộc vào HTTP request(method = GET )
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    //chỉ đưc gọi chính xác 1 lần bởi container
    public void destroy() {
    }
}