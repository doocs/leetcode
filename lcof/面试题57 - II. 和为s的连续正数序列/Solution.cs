public class Solution {
    public int[][] FindContinuousSequence(int target) {
        List<int[]> res = new List<int[]>();
        int p = 1, q = 2;
        while (p < q) {
            int s = (p + q) * (q - p + 1) >> 1;
            if (s == target) {
                List<int> tmp = new List<int>();
                for (int i = p; i < q + 1; i++) {
                    tmp.Add(i);
                }
                p += 1;
                res.Add(tmp.ToArray());
            } else if (s < target) {
                q += 1;
            } else {
                p += 1;
            }
        }
        return res.ToArray();
    }
}