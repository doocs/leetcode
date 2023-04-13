class Solution {
    public long countTheNumOfKFreeSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            g.computeIfAbsent(nums[i] % k, x -> new ArrayList<>()).add(nums[i]);
        }
        long ans = 1;
        for (var arr : g.values()) {
            int m = arr.size();
            long[] f = new long[m + 1];
            f[0] = 1;
            f[1] = 2;
            for (int i = 2; i <= m; ++i) {
                if (arr.get(i - 1) - arr.get(i - 2) == k) {
                    f[i] = f[i - 1] + f[i - 2];
                } else {
                    f[i] = f[i - 1] * 2;
                }
            }
            ans *= f[m];
        }
        return ans;
    }
}