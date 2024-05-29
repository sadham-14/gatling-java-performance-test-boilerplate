package core.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequestBodyGenerator {
    public static String getAddProductRequestPayload() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", 1);
        jsonObject.put("title", "#{productName}");
        jsonObject.put("description", "An apple mobile which is nothing like apple");
        jsonObject.put("price", 1749);
        jsonObject.put("discountPercentage", 12.96);
        jsonObject.put("rating", 4.69);
        jsonObject.put("stock", 94);
        jsonObject.put("brand", "Apple");
        jsonObject.put("category", "smartphones");
        jsonObject.put("thumbnail", "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg");

        JSONArray imagesArray = new JSONArray();

        imagesArray.put("https://cdn.dummyjson.com/product-images/1/1.jpg");
        imagesArray.put("https://cdn.dummyjson.com/product-images/1/2.jpg");
        imagesArray.put("https://cdn.dummyjson.com/product-images/1/3.jpg");
        imagesArray.put("https://cdn.dummyjson.com/product-images/1/4.jpg");
        imagesArray.put("https://cdn.dummyjson.com/product-images/1/thumbnail.jpg");
        jsonObject.put("images", imagesArray);

        return jsonObject.toString();
    }

    public static String getUpdateProductRequestPayload() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "#{productName}");

        return jsonObject.toString();
    }

    public static String getAuthenticationRequestPayload() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("username", "kminchelle");
        jsonObject.put("password", "0lelplR");
        jsonObject.put("expiresInMins", 120);

        return jsonObject.toString();
    }
}
