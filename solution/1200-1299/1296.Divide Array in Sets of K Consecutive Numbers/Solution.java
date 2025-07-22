class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        for (int x : nums) {
            if (cnt.getOrDefault(x, 0) > 0) {
                for (int y = x; y < x + k; ++y) {
                    if (cnt.merge(y, -1, Integer::sum) < 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
