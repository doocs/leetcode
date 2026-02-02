class Solution {
    public int longestSubsequence(int[] nums) {
        int ans = 0;
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int m = 32 - Integer.numberOfLeadingZeros(mx);
        for (int i = 0; i < m; i++) {
            List<Integer> arr = new ArrayList<>();
            for (int x : nums) {
                if (((x >> i) & 1) == 1) {
                    arr.add(x);
                }
            }
            ans = Math.max(ans, lis(arr));
        }
        return ans;
    }

    private int lis(List<Integer> arr) {
        List<Integer> g = new ArrayList<>();
        for (int x : arr) {
            int l = 0, r = g.size();
            while (l < r) {
                int mid = (l + r) >>> 1;
                if (g.get(mid) >= x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if (l == g.size()) {
                g.add(x);
            } else {
                g.set(l, x);
            }
        }
        return g.size();
    }
}
