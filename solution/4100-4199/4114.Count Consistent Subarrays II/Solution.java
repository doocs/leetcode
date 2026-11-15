class Solution {
    public long countConsistent(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        Arrays.fill(left, -1);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!stk.isEmpty() && (nums[stk.peek()] & x) == x) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }

        stk.clear();
        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] != x && (nums[stk.peek()] & x) == x) {
                stk.pop();
            }
            int right = stk.isEmpty() ? n : stk.peek();
            stk.push(i);
            ans += (long) (i - left[i]) * (right - i);
        }
        return ans;
    }
}