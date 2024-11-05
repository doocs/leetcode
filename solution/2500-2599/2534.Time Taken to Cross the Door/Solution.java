class Solution {
    public int[] timeTaken(int[] arrival, int[] state) {
        Deque<Integer>[] q = new Deque[2];
        Arrays.setAll(q, i -> new ArrayDeque<>());
        int n = arrival.length;
        int t = 0, i = 0, st = 1;
        int[] ans = new int[n];
        while (i < n || !q[0].isEmpty() || !q[1].isEmpty()) {
            while (i < n && arrival[i] <= t) {
                q[state[i]].add(i++);
            }
            if (!q[0].isEmpty() && !q[1].isEmpty()) {
                ans[q[st].poll()] = t;
            } else if (!q[0].isEmpty() || !q[1].isEmpty()) {
                st = q[0].isEmpty() ? 1 : 0;
                ans[q[st].poll()] = t;
            } else {
                st = 1;
            }
            ++t;
        }
        return ans;
    }
}
