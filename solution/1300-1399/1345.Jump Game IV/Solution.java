class Solution {
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> idx = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            idx.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        Deque<int[]> q = new LinkedList<>();
        Set<Integer> vis = new HashSet<>();
        vis.add(0);
        q.offer(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] e = q.pollFirst();
            int i = e[0], step = e[1];
            if (i == n - 1) {
                return step;
            }
            int v = arr[i];
            ++step;
            for (int j : idx.getOrDefault(v, new ArrayList<>())) {
                if (!vis.contains(j)) {
                    vis.add(j);
                    q.offer(new int[]{j, step});
                }
            }
            idx.remove(v);
            if (i + 1 < n && !vis.contains(i + 1)) {
                vis.add(i + 1);
                q.offer(new int[]{i + 1, step});
            }
            if (i - 1 >= 0 && !vis.contains(i - 1)) {
                vis.add(i - 1);
                q.offer(new int[]{i - 1, step});
            }
        }
        return -1;
    }
}