package servlet.response.header.enums;

public enum StatusCode {
    OK(200), REDIRECT(302);

    private final int statusCode;
    private StatusCode(int statusCode){
        this.statusCode =statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
