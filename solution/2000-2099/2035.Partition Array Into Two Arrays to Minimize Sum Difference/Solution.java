class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length >> 1;
        Map<Integer, Set<Integer>> f = new HashMap<>();
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int i = 0; i < (1 << n); ++i) {
            int s = 0, cnt = 0;
            int s1 = 0, cnt1 = 0;
            for (int j = 0; j < n; ++j) {
                if ((i & (1 << j)) != 0) {
                    s += nums[j];
                    ++cnt;
                    s1 += nums[n + j];
                    ++cnt1;
                } else {
                    s -= nums[j];
                    s1 -= nums[n + j];
                }
            }
            f.computeIfAbsent(cnt, k -> new HashSet<>()).add(s);
            g.computeIfAbsent(cnt1, k -> new HashSet<>()).add(s1);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= n; ++i) {
            List<Integer> fi = new ArrayList<>(f.get(i));
            List<Integer> gi = new ArrayList<>(g.get(n - i));
            Collections.sort(fi);
            Collections.sort(gi);
            for (int a : fi) {
                int left = 0, right = gi.size() - 1;
                int b = -a;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (gi.get(mid) >= b) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                ans = Math.min(ans, Math.abs(a + gi.get(left)));
                if (left > 0) {
                    ans = Math.min(ans, Math.abs(a + gi.get(left - 1)));
                }
            }
        }
        return ans;
    }
}