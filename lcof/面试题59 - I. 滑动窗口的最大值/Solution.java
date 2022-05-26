class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int index = 0, n = nums.length;
        if (k == 0 || n == 0) {
            return new int[0];
        }
        int[] res = new int[n - k + 1];
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) {
                q.pollLast();
            }
            q.addLast(i);
            if (q.peekFirst() == i - k) {
                q.pollFirst();
            }
            if (i >= k - 1) {
                res[index++] = nums[q.peekFirst()];
            }
        }
        return res;
    }
}