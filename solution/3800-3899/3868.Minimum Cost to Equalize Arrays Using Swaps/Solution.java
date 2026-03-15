class Solution {
    public int minCost(int[] nums1, int[] nums2) {
        Map<Integer, Integer> cnt2 = new HashMap<>();
        for (int x : nums2) {
            cnt2.merge(x, 1, Integer::sum);
        }

        Map<Integer, Integer> cnt1 = new HashMap<>();
        for (int x : nums1) {
            int c = cnt2.getOrDefault(x, 0);
            if (c > 0) {
                cnt2.put(x, c - 1);
            } else {
                cnt1.merge(x, 1, Integer::sum);
            }
        }

        int ans = 0;

        for (int v : cnt1.values()) {
            if ((v & 1) == 1) {
                return -1;
            }
            ans += v / 2;
        }

        for (int v : cnt2.values()) {
            if ((v & 1) == 1) {
                return -1;
            }
        }

        return ans;
    }
}
