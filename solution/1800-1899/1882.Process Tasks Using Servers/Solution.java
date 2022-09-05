class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int m = tasks.length, n = servers.length;
        PriorityQueue<int[]> idle
            = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] == b[1] ? a[2] - b[2] : a[1] - b[1];
            }
            return a[0] - b[0];
        });
        for (int i = 0; i < n; ++i) {
            idle.offer(new int[] {servers[i], i});
        }
        int[] res = new int[m];
        int j = 0;
        for (int start = 0; start < m; ++start) {
            int cost = tasks[start];
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                int[] item = busy.poll();
                idle.offer(new int[] {item[1], item[2]});
            }
            if (!idle.isEmpty()) {
                int[] item = idle.poll();
                res[j++] = item[1];
                busy.offer(new int[] {start + cost, item[0], item[1]});
            } else {
                int[] item = busy.poll();
                res[j++] = item[2];
                busy.offer(new int[] {item[0] + cost, item[1], item[2]});
            }
        }
        return res;
    }
}