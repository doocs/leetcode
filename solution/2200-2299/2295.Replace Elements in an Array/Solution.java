class Solution {
    public int[] arrayChange(int[] nums, int[][] operations) {
        int n = nums.length;
        Map<Integer, Integer> d = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            d.put(nums[i], i);
        }
        for (var op : operations) {
            int x = op[0], y = op[1];
            nums[d.get(x)] = y;
            d.put(y, d.get(x));
        }
        return nums;
    }
}