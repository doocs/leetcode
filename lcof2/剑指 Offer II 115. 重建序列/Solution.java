class Solution {
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        int n = nums.length;
        int[] indeg = new int[n];
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] seq : sequences) {
            for (int i = 1; i < seq.length; ++i) {
                int a = seq[i - 1] - 1, b = seq[i] - 1;
                g[a].add(b);
                indeg[b]++;
            }
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            if (q.size() > 1) {
                return false;
            }
            int i = q.poll();
            for (int j : g[i]) {
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return true;
    }
}