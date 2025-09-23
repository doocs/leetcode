public class Solution {
    public int CanBeTypedWords(string text, string brokenLetters) {
        bool[] s = new bool[26];
        foreach (char c in brokenLetters) {
            s[c - 'a'] = true;
        }
        int ans = 0;
        string[] words = text.Split(' ');
        foreach (string w in words) {
            foreach (char c in w) {
                if (s[c - 'a']) {
                    --ans;
                    break;
                }
            }
            ++ans;
        }
        return ans;
    }
}
