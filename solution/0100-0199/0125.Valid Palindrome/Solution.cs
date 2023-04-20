public class Solution {
    public bool IsPalindrome(string s) {
        int i = 0, j = s.Length - 1;
        while (i < j) {
            if (!char.IsLetterOrDigit(s[i])) {
                ++i;
            } else if (!char.IsLetterOrDigit(s[j])) {
                --j;
            } else if (char.ToLower(s[i++]) != char.ToLower(s[j--])) {
                return false;
            }
        }
        return true;
    }
}