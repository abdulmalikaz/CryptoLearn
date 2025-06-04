public class LoginPage {
    private AuthenticationController authController;

    public LoginPage() {
        this.authController = new AuthenticationController();
    }

    public boolean displayLogin(String username, String password) {
        System.out.println("Attempting to log in...");
        return authController.handleLogin(username, password);
    }

    // Changed from void to boolean to fix the type mismatch
    public boolean displaySignUp(String username, String password) {
        System.out.println("Attempting to sign up...");
        boolean success = authController.handleRegistration(username, password);
        if (success) {
            System.out.println("Sign-up successful!");
            return true;
        } else {
            System.out.println("Sign-up failed.");
            return false;
        }
    }

    public AuthenticationController getAuthController() {
        return authController;
    }
}