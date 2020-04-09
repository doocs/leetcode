class Solution {
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + A[i];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(0);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; ++i) {
            while (!deque.isEmpty() && s[i] - s[deque.peekFirst()] >= K) {
                res = Math.min(res, i - deque.pollFirst());
            }
            while (!deque.isEmpty() && s[i] <= s[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offer(i);
        }
        return res != Integer.MAX_VALUE ? res : -1;
    }
}
