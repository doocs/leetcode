public class Solution {
    private char[] cs;
    private List<string> ans = new List<string>();

    public string[] Permutation(string s) {
        cs = s.ToCharArray();
        dfs(0);
        return ans.ToArray();
    }

    private void dfs(int i) {
        if (i == cs.Length - 1) {
            ans.Add(new string(cs));
            return;
        }
        var vis = new HashSet<char>();
        for (int j = i; j < cs.Length; ++j) {
            if (vis.Add(cs[j])) {
                (cs[i], cs[j]) = (cs[j], cs[i]);
                dfs(i + 1);
                (cs[i], cs[j]) = (cs[j], cs[i]);
            }
        }
    }
}
