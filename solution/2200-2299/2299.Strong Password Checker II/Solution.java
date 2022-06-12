class Solution {
    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) {
            return false;
        }
        int ans = 0;
        char prev = '.';
        for (char c : password.toCharArray()) {
            if (prev == c) {
                return false;
            }
            prev = c;
            if (Character.isLowerCase(c)) {
                ans |= 1;
            } else if (Character.isUpperCase(c)) {
                ans |= 2;
            } else if (Character.isDigit(c)) {
                ans |= 4;
            } else {
                ans |= 8;
            }
        }
        return ans == 15;
    }
}