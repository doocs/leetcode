class Solution {
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        int[] ans = new int[n];
        TreeSet<Integer>[] ts = new TreeSet[] {new TreeSet<>(), new TreeSet<>()};
        for (int i = 0; i < n; ++i) {
            ts[i % 2].add(i);
            ans[i] = i == p ? 0 : -1;
        }
        ts[p % 2].remove(p);
        for (int i : banned) {
            ts[i % 2].remove(i);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(p);
        while (!q.isEmpty()) {
            int x = q.poll();
            int i = Math.abs(x - k + 1);
            Integer j = ts[i % 2].ceiling(i);
            while (j != null && j < n - Math.abs(n - x - k)) {
                q.offer(j);
                ans[j] = ans[x] + 1;
                ts[i % 2].remove(j);
                j = ts[i % 2].higher(j);
            }
        }
        return ans;
    }
}