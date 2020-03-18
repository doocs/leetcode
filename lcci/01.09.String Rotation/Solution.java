class Solution {
    public boolean isFlipedString(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 != len2) {
            return false;
        }
        if ((len1 == 0 && len2 == 0) || (s1.equals(s2))) {
            return true;
        }

        for (int i = 0; i < len1; ++i) {
            s1 = flip(s1);
            if (s1.equals(s2)) {
                return true;
            }
        }
        return false;

    }

    private String flip(String s) {
        return s.substring(1) + s.charAt(0);
    }
}