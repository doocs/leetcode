class Solution {
    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int s = 0;
        for (int x : nums) {
            s ^= x;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            for (int j : g[i]) {
                int s1 = componentXor(nums, g, i, j);
                ans = Math.min(ans, collect(nums, g, i, j, s, s1));
            }
        }
        return ans;
    }

    private int componentXor(int[] nums, List<Integer>[] g, int root, int ban) {
        int n = nums.length;
        int[] sub = new int[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {root, ban, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push(new int[] {j, i, 0});
                    }
                }
            } else {
                int res = nums[i];
                for (int j : g[i]) {
                    if (j != fa) {
                        res ^= sub[j];
                    }
                }
                sub[i] = res;
            }
        }
        return sub[root];
    }

    private int collect(int[] nums, List<Integer>[] g, int root, int ban, int s, int s1) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        int[] sub = new int[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {root, ban, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push(new int[] {j, i, 0});
                    }
                }
            } else {
                int res = nums[i];
                for (int j : g[i]) {
                    if (j != fa) {
                        int s2 = sub[j];
                        res ^= s2;
                        int mx = Math.max(Math.max(s ^ s1, s2), s1 ^ s2);
                        int mn = Math.min(Math.min(s ^ s1, s2), s1 ^ s2);
                        ans = Math.min(ans, mx - mn);
                    }
                }
                sub[i] = res;
            }
        }
        return ans;
    }
}
