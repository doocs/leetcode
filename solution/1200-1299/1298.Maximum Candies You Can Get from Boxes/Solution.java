class Solution {
    public int maxCandies(
        int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int ans = 0;
        int n = status.length;
        boolean[] has = new boolean[n];
        boolean[] took = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i : initialBoxes) {
            has[i] = true;
            if (status[i] == 1) {
                ans += candies[i];
                took[i] = true;
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int i = q.poll();
            for (int k : keys[i]) {
                status[k] = 1;
                if (has[k] && !took[k]) {
                    ans += candies[k];
                    took[k] = true;
                    q.offer(k);
                }
            }
            for (int j : containedBoxes[i]) {
                has[j] = true;
                if (status[j] == 1 && !took[j]) {
                    ans += candies[j];
                    took[j] = true;
                    q.offer(j);
                }
            }
        }
        return ans;
    }
}