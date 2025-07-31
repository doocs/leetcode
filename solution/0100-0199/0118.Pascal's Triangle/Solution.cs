public class Solution {
    public IList<IList<int>> Generate(int numRows) {
        var f = new List<IList<int>> { new List<int> { 1 } };
        for (int i = 1; i < numRows; ++i) {
            var g = new List<int> { 1 };
            for (int j = 1; j < f[i - 1].Count; ++j) {
                g.Add(f[i - 1][j - 1] + f[i - 1][j]);
            }
            g.Add(1);
            f.Add(g);
        }
        return f;
    }
}
