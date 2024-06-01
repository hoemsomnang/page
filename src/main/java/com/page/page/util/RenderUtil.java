package com.page.page.util;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RenderUtil {
    public static void renderJson(HttpServletResponse response, ResponseData<Object>jsonObject) {

        try {
            String json = new Gson().toJson(jsonObject);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
