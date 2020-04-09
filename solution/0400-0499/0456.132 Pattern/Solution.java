class Solution {
    public boolean find132pattern(int[] nums) {
        int ak = Integer.MIN_VALUE;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            if (nums[i] < ak) {
                return true;
            }
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                ak = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }
}
