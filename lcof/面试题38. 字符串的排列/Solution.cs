public class Solution {
    public string[] Permutation(string s) {
        var data = s.ToCharArray();
        var ans = new List<string>();
        DFS(data, 0, ans);
        return ans.ToArray();
    }

    void DFS(char[] s, int idx, List<string> ans) {
        if (idx == s.Length) {
            ans.Add(new string(s));
            return;
        }
        var set = new HashSet<char>();
        for (int i = idx; i < s.Length; i++) {
            if (set.Add(s[i])) {
                (s[i], s[idx]) = (s[idx], s[i]);
                DFS(s, idx+1, ans);
                (s[i], s[idx]) = (s[idx], s[i]);
            }
        }
    }
}