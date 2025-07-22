class Solution {
    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if (n == 1) {
            return "";
        }
        char[] s = palindrome.toCharArray();
        int i = 0;
        while (i < n / 2 && s[i] == 'a') {
            ++i;
        }
        if (i == n / 2) {
            s[n - 1] = 'b';
        } else {
            s[i] = 'a';
        }
        return String.valueOf(s);
    }
}
