class Solution {
    private int[] p;

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        var nums = new int[n + pipes.length][0];
        int j = 0;
        for (var pipe : pipes) {
            nums[j++] = pipe;
        }
        for (int i = 0; i < n; ++i) {
            nums[j++] = new int[]{0, i + 1, wells[i]};
        }
        Arrays.sort(nums, (a, b) -> a[2] - b[2]);
        p = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            p[i] = i;
        }
        int ans = 0;
        for (var x : nums) {
            int pa = find(x[0]), pb = find(x[1]);
            if (pa == pb) {
                continue;
            }
            ans += x[2];
            p[pa] = pb;
            if (--n == 0) {
                break;
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}