class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        int start = 0 , end = s.length() - 1;
        while (start < end) {
            char c = ' ';
            while (start < end) {
                c = s.charAt(start);
                if (c >= 'a' && c <= 'z' || c >= '0' && c <= '9') break;
                if (c >= 'A' && c <= 'Z') {
                    c = (char) (c - 'A' + 'a');
                    break;
                }
                start++;
            }
            char b = ' ';
            while (start < end) {
                b = s.charAt(end);
                if (b >= 'a' && b <= 'z' || b >= '0' && b <= '9') break;
                if (b >= 'A' && b <= 'Z') {
                    b = (char) (b - 'A' + 'a');
                    break;
                }
                end--;
            }
            if (start < end) {
                if (c != b) return false;
                start++;
                end--;
            }
        }
        return true;
    }
}