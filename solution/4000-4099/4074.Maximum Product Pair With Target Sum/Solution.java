class Solution {
    public int[] maxProductPair(int[] nums, int target) {
        Map<Integer, Integer> pos = new HashMap<>();
        int mx = Integer.MIN_VALUE;
        int[] ans = {-1, -1};
        for (int i = 0; i < nums.length; ++i) {
            int x = nums[i], y = target - x;
            if (x != y && x * y > mx && pos.containsKey(y)) {
                int j = pos.get(y);
                mx = x * y;
                ans = x > y ? new int[] {i, j} : new int[] {j, i};
            }
            pos.put(x, i);
        }
        return ans;
    }
}