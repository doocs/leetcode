class Solution {
    public boolean canMakeSubsequence(String str1, String str2) {
        int i = 0, n = str2.length();
        for (char c : str1.toCharArray()) {
            char d = c == 'z' ? 'a' : (char) (c + 1);
            if (i < n && (str2.charAt(i) == c || str2.charAt(i) == d)) {
                ++i;
            }
        }
        return i == n;
    }
}