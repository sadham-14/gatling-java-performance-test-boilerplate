package core.constant;

public class ApiPayloadConstant {
    // API Names
    public static final String GET_AUTH_TOKEN_API_NAME = "[POST] Get Authentication Token";
    public static final String ADD_PRODUCT_API_NAME = "[POST] Add Product";
    public static final String UPDATE_PRODUCT_API_NAME = "[PUT] Update Product";
    public static final String GET_ALL_PRODUCTS_API_NAME = "[GET] Get All Products";

    // API Endpoints
    public static final String AUTHORIZATION_ENDPOINT = "/auth/login";
    public static final String PRODUCTS_ENDPOINT = "/auth/products";
    public static final String ADD_PRODUCT_ENDPOINT = PRODUCTS_ENDPOINT + "/add";

    // Authentication
    public static final String AUTH_HEADER_NAME = "Authorization";
    public static final String AUTH_TOKEN_VARIABLE = "token";
    public static final String AUTH_TOKEN_PLACEHOLDER = "Bearer #{" + AUTH_TOKEN_VARIABLE + "}";

    // CSV File Paths
    public static final String PRODUCT_NAMES_CSV_FILE_PATH = "data/product_names.csv";

    // Response Status Codes
    public static final String RESPONSE_STATUS_CODE = "responseStatusCode";
    public static final int HTTP_OK = 200;
    public static final int HTTP_CREATED = 201;
    public static final int HTTP_INTERNAL_SERVER_ERROR = 500;
}
