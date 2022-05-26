class Solution {
    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetter(chars[i])) {
                ++i;
            }
            while (i < j && !Character.isLetter(chars[j])) {
                --j;
            }
            if (i < j) {
                char t = chars[i];
                chars[i] = chars[j];
                chars[j] = t;
                ++i;
                --j;
            }
        }
        return new String(chars);
    }
}