class Solution {
    public int minimumValueSum(int[] nums, int[] andValues) {
        final int inf = 1 << 29;
        int n = nums.length, m = andValues.length;
        final int stride = 100001;
        Map<Integer, Integer> f = new HashMap<>();
        f.put(0, 0);
        for (int i = 0; i < n; ++i) {
            Map<Integer, Integer> g = new HashMap<>();
            for (var e : f.entrySet()) {
                int key = e.getKey(), cost = e.getValue();
                int j = key / stride;
                int a = key % stride - 1;
                if (n - i < m - j) {
                    continue;
                }
                int na = a & nums[i];
                if (na < andValues[j]) {
                    continue;
                }
                int nk = j * stride + na + 1;
                g.put(nk, Math.min(g.getOrDefault(nk, inf), cost));
                if (na == andValues[j]) {
                    int t = cost + nums[i];
                    if (j + 1 == m) {
                        if (i == n - 1) {
                            int done = m * stride;
                            g.put(done, Math.min(g.getOrDefault(done, inf), t));
                        }
                    } else {
                        int nk2 = (j + 1) * stride;
                        g.put(nk2, Math.min(g.getOrDefault(nk2, inf), t));
                    }
                }
            }
            f = g;
        }
        int ans = f.getOrDefault(m * stride, inf);
        return ans >= inf ? -1 : ans;
    }
}
