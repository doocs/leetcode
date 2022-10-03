class Solution {
    public int minChanges(int[] nums, int k) {
        int n = 1 << 10;
        Map<Integer, Integer>[] cnt = new Map[k];
        int[] size = new int[k];
        for (int i = 0; i < k; ++i) {
            cnt[i] = new HashMap<>();
        }
        for (int i = 0; i < nums.length; ++i) {
            cnt[i % k].put(nums[i], cnt[i % k].getOrDefault(nums[i], 0) + 1);
            size[i % k]++;
        }
        int[] f = new int[n];
        Arrays.fill(f, 0x3f3f3f3f);
        f[0] = 0;
        for (int i = 0; i < k; ++i) {
            int[] g = new int[n];
            Arrays.fill(g, min(f) + size[i]);
            for (int j = 0; j < n; ++j) {
                for (var e : cnt[i].entrySet()) {
                    int v = e.getKey(), c = e.getValue();
                    g[j] = Math.min(g[j], f[j ^ v] + size[i] - c);
                }
            }
            f = g;
        }
        return f[0];
    }

    private int min(int[] arr) {
        int mi = arr[0];
        for (int v : arr) {
            mi = Math.min(mi, v);
        }
        return mi;
    }
}