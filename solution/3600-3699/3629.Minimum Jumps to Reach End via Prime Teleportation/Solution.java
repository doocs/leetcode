class Solution {
    private static final int mx = 1000001;
    private static final List<Integer>[] factors = new List[mx];

    static {
        for (int i = 0; i < mx; i++) {
            factors[i] = new ArrayList<>();
        }
        for (int i = 2; i < mx; i++) {
            if (factors[i].isEmpty()) {
                for (int j = i; j < mx; j += i) {
                    factors[j].add(i);
                }
            }
        }
    }

    public int minJumps(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int p : factors[x]) {
                g.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
            }
        }
        int ans = 0;
        boolean[] vis = new boolean[n];
        vis[0] = true;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        while (true) {
            Deque<Integer> nq = new ArrayDeque<>();
            for (int i : q) {
                if (i == n - 1) {
                    return ans;
                }
                List<Integer> idx = g.getOrDefault(nums[i], new ArrayList<>());
                idx.add(i + 1);
                if (i > 0) {
                    idx.add(i - 1);
                }
                for (int j : idx) {
                    if (!vis[j]) {
                        vis[j] = true;
                        nq.offer(j);
                    }
                }
                idx.clear();
            }
            q = nq;
            ans++;
        }
    }
}
