public class Solution {
    public string Convert(string s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int n = s.Length;
        StringBuilder[] g = new StringBuilder[numRows];
        for (int j = 0; j < numRows; ++j) {
            g[j] = new StringBuilder();
        }
        int i = 0, k = -1;
        foreach (char c in s.ToCharArray()) {
            g[i].Append(c);
            if (i == 0 || i == numRows - 1) {
                k = -k;
            }
            i += k;
        }
        StringBuilder ans = new StringBuilder();
        foreach (StringBuilder t in g) {
            ans.Append(t);
        }
        return ans.ToString();
    }
}