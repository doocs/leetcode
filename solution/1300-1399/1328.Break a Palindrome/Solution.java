class Solution {
    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if (n == 1) {
            return "";
        }
        char[] cs = palindrome.toCharArray();
        int i = 0;
        while (i < n / 2 && cs[i] == 'a') {
            ++i;
        }
        if (i == n / 2) {
            cs[n - 1] = 'b';
        } else {
            cs[i] = 'a';
        }
        return String.valueOf(cs);
    }
}