public class Solution {
    public string ReverseWords(string s) {
        List<string> words = new List<string>();
        int n = s.Length;
        for (int i = 0; i < n;) {
            while (i < n && s[i] == ' ') {
                ++i;
            }
            if (i < n) {
                System.Text.StringBuilder t = new System.Text.StringBuilder();
                int j = i;
                while (j < n && s[j] != ' ') {
                    t.Append(s[j++]);
                }
                words.Add(t.ToString());
                i = j;
            }
        }
        words.Reverse();
        return string.Join(" ", words);
    }
}