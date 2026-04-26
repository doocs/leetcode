class Solution {
    public List<Integer> findValidElements(int[] nums) {
        int n = nums.length;
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], nums[i]);
        }
        int left = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x > left || i == n - 1 || x > right[i + 1]) {
                ans.add(x);
            }
            left = Math.max(left, x);
        }
        return ans;
    }
}
