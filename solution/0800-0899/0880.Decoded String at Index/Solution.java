class Solution {
    public String decodeAtIndex(String s, int k) {
        long m = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (Character.isDigit(s.charAt(i))) {
                m *= (s.charAt(i) - '0');
            } else {
                ++m;
            }
        }
        for (int i = s.length() - 1;; --i) {
            k %= m;
            if (k == 0 && !Character.isDigit(s.charAt(i))) {
                return String.valueOf(s.charAt(i));
            }
            if (Character.isDigit(s.charAt(i))) {
                m /= (s.charAt(i) - '0');
            } else {
                --m;
            }
        }
    }
}