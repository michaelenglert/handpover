package org.appdynamics.handpover.config;

/**
 * Created by michi on 15.08.16.
 */
public class Globals {
    public static String URL;

    public static final String CONTROLLER_HTTP = "http://";
    public static final String CONTROLLER_HTTPS = "https://";
    public static final String CONTROLLER_HTTP_PORT = "80";
    public static final String CONTROLLER_HTTPS_PORT = "443";
    public static final String CONTROLLER_HTTP_DEFAULT_PORT = "8090";
    public static final String CONTROLLER_HTTPS_DEFAULT_PORT = "8181";
    public static final String CONTROLLER_ROOT = "/controller";
    public static final String CONTROLLER_ADMIN = "admin";

    public static final String COLON = ":";
    public static final String PIPE = "|";
    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String ROOT = "/";
    public static final String OPENING_SBRACKETS = "[";
    public static final String CLOSING_SBRACKETS = "]";
    public static final String AT = "@";
    public static final String REST_AUTH_METHOD = "Basic ";
    public static final String REST_AUTH_KEY = "Authorization";
    public static final String COOKIE_CSRF = "X-CSRF-TOKEN";
    public static final String COOKIE_JSESSION = "JSESSIONID";
    public static final String AUDIT_TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String AUDIT_TIMESTAMP_END = "-0000";

    public static final String API_LOGIN = "/auth?action=login";
    public static final String API_APP_EXPORT = "/ConfigObjectImportExportServlet?applicationId=";
    public static final String API_AUDIT = "/ControllerAuditHistory";
    public static final String API_CONTROLLER_CONFIG = "/rest/configuration";
    public static final String API_DASHBOARD_LIST = "restui/dashboards/list2";
    public static final String API_GET_USER = "/restui/user/getUser";
    public static final String API_DASHBOARD_EXPORT = "/CustomDashboardImportExportServlet?dashboardId=";
    public static final String API_APP_LIST = "/rest/applications";
    public static final String API_OUTPUT = "?output=JSON";
    public static final String API_AUDIT_START = "?startTime=";
    public static final String API_AUDIT_END = "&endTime=";
    public static final String API_CONTROLLER_LOGS = "/FileUploadServlet?action=CONTROLLER_LOGS";
    public static final int API_AUDIT_DAYS = -90;

    public static final String EXCEL_FILE = "export.xlsx";
    public static final String CONTROLLER_LOG_FILES = "controller-logs.zip";
    public static final String OUTPUT_FOLDER = "output/";
    public static final String OUTPUT_FILE = "output.zip";
    public static final String EXCEL_CONTROLLER_SETTINGS = "controller_settings";
    public static final String EXCEL_CONTROLLER_AUDIT = "controller_audit";

    public static final String ERROR_HTTP = "Some Connectivity Issue - HTTP error code ";
    public static final String ERROR_NO_ADMIN = "Your are not admin. Please use an admin user for the AppDynamics Controller.";
}
