class Solution {
    public int validSubarrays(int[] nums) {
        int n = nums.length;
        Deque<Integer> stk = new ArrayDeque<>();
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                stk.pop();
            }
            ans += (stk.isEmpty() ? n : stk.peek()) - i;

            stk.push(i);
        }
        return ans;
    }
}