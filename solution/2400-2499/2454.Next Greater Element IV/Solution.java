class Solution {
    public int[] secondGreaterElement(int[] nums) {
        Deque<Integer> stk = new ArrayDeque<>();
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            while (!q.isEmpty() && q.peek()[0] < v) {
                ans[q.peek()[1]] = v;
                q.poll();
            }
            while (!stk.isEmpty() && nums[stk.peek()] < v) {
                q.offer(new int[] {nums[stk.peek()], stk.pop()});
            }
            stk.push(i);
        }
        return ans;
    }
}