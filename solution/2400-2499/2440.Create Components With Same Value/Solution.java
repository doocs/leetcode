class Solution {
    private List<Integer>[] g;
    private int[] nums;
    private int t;

    public int componentValue(int[] nums, int[][] edges) {
        int n = nums.length;
        g = new List[n];
        this.nums = nums;
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int s = sum(nums), mx = max(nums);
        for (int k = Math.min(n, s / mx); k > 1; --k) {
            if (s % k == 0) {
                t = s / k;
                if (dfs(0, -1) == 0) {
                    return k - 1;
                }
            }
        }
        return 0;
    }

    private int dfs(int i, int fa) {
        int x = nums[i];
        for (int j : g[i]) {
            if (j != fa) {
                int y = dfs(j, i);
                if (y == -1) {
                    return -1;
                }
                x += y;
            }
        }
        if (x > t) {
            return -1;
        }
        return x < t ? x : 0;
    }

    private int sum(int[] arr) {
        int x = 0;
        for (int v : arr) {
            x += v;
        }
        return x;
    }

    private int max(int[] arr) {
        int x = arr[0];
        for (int v : arr) {
            x = Math.max(x, v);
        }
        return x;
    }
}