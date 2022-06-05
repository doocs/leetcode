class Solution {
    public int[] arrayChange(int[] nums, int[][] operations) {
        int n = nums.length;
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            d.put(nums[i], i);
        }
        for (int[] op : operations) {
            int a = op[0], b = op[1];
            int idx = d.get(a);
            d.remove(a);
            d.put(b, idx);
        }
        int[] ans = new int[n];
        d.forEach((v, i) -> {
            ans[i] = v;
        });
        return ans;
    }
}