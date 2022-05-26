class Solution {
    public boolean checkValidString(String s) {
        int n = s.length();
        char[] a = s.toCharArray();
        int left = 0, asterisk = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == '(') {
                left++;
            } else if (a[i] == ')') {
                if (left > 0) {
                    left--;
                } else if (asterisk > 0) {
                    asterisk--;
                } else {
                    return false;
                }
            } else {
                asterisk++;
            }
        }
        int right = 0;
        asterisk = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] == ')') {
                right++;
            } else if (a[i] == '(') {
                if (right > 0) {
                    right--;
                } else if (asterisk > 0) {
                    asterisk--;
                } else {
                    return false;
                }
            } else {
                asterisk++;
            }
        }
        return true;
    }
}
