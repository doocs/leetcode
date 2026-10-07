class Solution {
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;
        int[] p = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            p[i + 1] = (p[i] + nums[i]) % k;
            if (p[i + 1] < 0) {
                p[i + 1] += k;
            }
        }

        int[] first = new int[k];
        Arrays.fill(first, -1);
        for (int i = 0; i <= n; ++i) {
            if (first[p[i]] == -1) {
                first[p[i]] = i;
            }
        }

        Integer[] order = new Integer[k];
        int m = 0;
        for (int q = 0; q < k; ++q) {
            if (first[q] != -1) {
                order[m++] = q;
            }
        }

        Arrays.sort(order, 0, m, (a, b) -> Integer.compare(first[a], first[b]));

        int[] pos = new int[k];
        int[] best = new int[k];
        Arrays.fill(best, Integer.MAX_VALUE);
        for (int q = 0; q < k; ++q) {
            if (first[q] != -1) {
                best[q] = first[q];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int a = nums[i] % k;
            if (a < 0) {
                a += k;
            }

            while (pos[a] < m && first[order[pos[a]]] <= i) {
                int q = order[pos[a]++];
                int t = (q + 2 * a) % k;
                best[t] = Math.min(best[t], first[q]);
            }

            int s = p[i + 1];
            if (best[s] != Integer.MAX_VALUE) {
                ans = Math.max(ans, i + 1 - best[s]);
            }
        }
        return ans;
    }
}