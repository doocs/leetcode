class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int len = (n << 1) - 1;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; ++i) {
            int x = nums[i < n ? i : i - n];
            while (!stack.isEmpty() && x > nums[stack.peek()]) {
                res[stack.pop()] = x;
            }
            if (i < n) {
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            res[stack.pop()] = -1;
        }
        return res;
    }
}
