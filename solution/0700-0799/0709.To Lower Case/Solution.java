class Solution {
    public String toLowerCase(String str) {
        int n;
        if (str == null || (n = str.length()) == 0) return str;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            boolean isUpper = chars[i] >= 'A' && chars[i] <= 'Z';
            if (isUpper) chars[i] += 32;
        }
        return new String(chars);
    }
}