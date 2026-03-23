class Solution {
    public long countGoodSubarrays(int[] nums) {
        int n = nums.length;

        int[] l = new int[n];
        Arrays.fill(l, -1);
        Deque<Integer> stk = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] < x && (nums[stk.peek()] | x) == x) {
                stk.pop();
            }
            l[i] = stk.isEmpty() ? -1 : stk.peek();
            stk.push(i);
        }

        int[] r = new int[n];
        Arrays.fill(r, n);
        stk.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stk.isEmpty() && (nums[stk.peek()] | nums[i]) == nums[i]) {
                stk.pop();
            }
            r[i] = stk.isEmpty() ? n : stk.peek();
            stk.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (long) (i - l[i]) * (r[i] - i);
        }
        return ans;
    }
}
