class Solution {
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            g.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        boolean[] vis = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        vis[0] = true;
        for (int ans = 0;; ++ans) {
            for (int k = q.size(); k > 0; --k) {
                int i = q.poll();
                if (i == n - 1) {
                    return ans;
                }
                for (int j : g.get(arr[i])) {
                    if (!vis[j]) {
                        vis[j] = true;
                        q.offer(j);
                    }
                }
                g.get(arr[i]).clear();
                for (int j : new int[] {i - 1, i + 1}) {
                    if (0 <= j && j < n && !vis[j]) {
                        vis[j] = true;
                        q.offer(j);
                    }
                }
            }
        }
    }
}
