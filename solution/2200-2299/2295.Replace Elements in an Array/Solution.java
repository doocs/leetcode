class Solution {
    public int[] arrayChange(int[] nums, int[][] operations) {
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            d.put(nums[i], i);
        }
        for (var op : operations) {
            int a = op[0], b = op[1];
            nums[d.get(a)] = b;
            d.put(b, d.get(a));
        }
        return nums;
    }
}