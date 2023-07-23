class Solution {
public:
    string breakPalindrome(string palindrome) {
        int n = palindrome.size();
        if (n == 1) {
            return "";
        }
        int i = 0;
        while (i < n / 2 && palindrome[i] == 'a') {
            ++i;
        }
        if (i == n / 2) {
            palindrome[n - 1] = 'b';
        } else {
            palindrome[i] = 'a';
        }
        return palindrome;
    }
};