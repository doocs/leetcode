public class Solution {
    public IList<IList<int>> CombinationSum3(int k, int n) {
        List<IList<int>> ans = new List<IList<int>>();
        for (int mask = 0; mask < 1 << 9; ++mask) {
            if (bitCount(mask) == k) {
                List<int> t = new List<int>();
                int s = 0;
                for (int i = 0; i < 9; ++i) {
                    if ((mask >> i & 1) == 1) {
                        s += i + 1;
                        t.Add(i + 1);
                    }
                }
                if (s == n) {
                    ans.Add(t);
                }
            }
        }
        return ans;
    }

    private int bitCount(int x) {
        int cnt = 0;
        while (x > 0) {
            x -= x & -x;
            ++cnt;
        }
        return cnt;
    }
}
