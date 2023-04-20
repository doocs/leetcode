public class Solution {
    public int LengthOfLastWord(string s) {
        int i = s.Length - 1;
        while (i >= 0 && s[i] == ' ') {
            --i;
        }
        int j = i;
        while (j >= 0 && s[j] != ' ') {
            --j;
        }
        return i - j;
    }
}