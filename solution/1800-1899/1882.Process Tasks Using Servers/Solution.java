class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        PriorityQueue<int[]> idle = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Long.compare(a[1], b[1]);
            }
            return Long.compare(a[2], b[2]);
        });
        for (int i = 0; i < n; i++) {
            idle.offer(new int[] {servers[i], i});
        }
        int m = tasks.length;
        int[] ans = new int[m];
        for (int j = 0; j < m; ++j) {
            int t = tasks[j];
            while (!busy.isEmpty() && busy.peek()[0] <= j) {
                long[] p = busy.poll();
                idle.offer(new int[] {(int) p[1], (int) p[2]});
            }
            if (!idle.isEmpty()) {
                int i = idle.poll()[1];
                ans[j] = i;
                busy.offer(new long[] {(long) j + t, servers[i], i});
            } else {
                long[] p = busy.poll();
                int i = (int) p[2];
                ans[j] = i;
                busy.offer(new long[] {p[0] + t, p[1], i});
            }
        }
        return ans;
    }
}
