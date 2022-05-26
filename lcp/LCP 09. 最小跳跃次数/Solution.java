class Solution {
    public int minJump(int[] jump) {
        int n = jump.length;
        boolean[] vis = new boolean[n + 1];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        vis[0] = true;
        int ans = 0;
        int mx = 1;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                int i = q.poll();
                int j = i + jump[i];
                if (j >= n) {
                    return ans + 1;
                }
                if (!vis[j]) {
                    q.offer(j);
                    vis[j] = true;
                }
                for (j = mx; j < i; ++j) {
                    if (!vis[j]) {
                        q.offer(j);
                        vis[j] = true;
                    }
                }
                mx = Math.max(mx, i + 1);
            }
            ++ans;
        }
        return -1;
    }
}