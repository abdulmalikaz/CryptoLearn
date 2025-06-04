public class InputValidator {

    // Validate username: alphanumeric and between 5-15 characters, no SQL injection symbols
    public boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9]{5,15}$");
    }

    // Validate password: at least 5 characters, one uppercase, one number, one special character, no SQL symbols
//  password validation to disallow SQL injection symbols
    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?!.*[';\"--]).{8,}$");
    }


    // Check for potential SQL injection symbols
    public boolean hasSqlInjectionRisk(String input) {
        // Check for SQL-specific characters or keywords
        String[] riskyPatterns = {";", "--", "'", "\"", "/*", "*/", "xp_", "OR", "AND"};
        for (String pattern : riskyPatterns) {
            if (input.toUpperCase().contains(pattern)) {
                return true; // Found SQL injection risk
            }
        }
        return false; // Input is safe
    }
}
