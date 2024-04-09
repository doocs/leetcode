public class Solution {
    private int m;
    private int n;
    private int[][] requests;

    public int MaximumRequests(int n, int[][] requests) {
        m = requests.Length;
        this.n = n;
        this.requests = requests;
        int ans = 0;
        for (int mask = 0; mask < (1 << m); ++mask) {
            int cnt = CountBits(mask);
            if (ans < cnt && Check(mask)) {
                ans = cnt;
            }
        }
        return ans;
    }

    private bool Check(int mask) {
        int[] cnt = new int[n];
        for (int i = 0; i < m; ++i) {
            if (((mask >> i) & 1) == 1) {
                int f = requests[i][0], t = requests[i][1];
                --cnt[f];
                ++cnt[t];
            }
        }
        foreach (int v in cnt) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }

    private int CountBits(int n) {
        int count = 0;
        while (n > 0) {
            n -= n & -n;
            ++count;
        }
        return count;
    }
}