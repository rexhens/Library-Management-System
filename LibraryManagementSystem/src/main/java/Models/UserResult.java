package Models;

public class UserResult {
    private final User user;
    private final String errorMessage;

    public UserResult(User user, String errorMessage) {
        this.user = user;
        this.errorMessage = errorMessage;
    }

    public User getUser() {
        return user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
