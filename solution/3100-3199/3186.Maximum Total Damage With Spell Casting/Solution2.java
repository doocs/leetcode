class Solution {
    public long maximumTotalDamage(int[] power) {
        Arrays.sort(power);
        int n = power.length;
        Map<Integer, Integer> cnt = new HashMap<>(n);
        int[] nxt = new int[n];
        for (int i = 0; i < n; ++i) {
            cnt.merge(power[i], 1, Integer::sum);
            int l = Arrays.binarySearch(power, power[i] + 3);
            l = l < 0 ? -l - 1 : l;
            nxt[i] = l;
        }
        long[] f = new long[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            int j = i + cnt.get(power[i]);
            long a = j <= n ? f[j] : 0;
            long b = 1L * power[i] * cnt.get(power[i]) + f[nxt[i]];
            f[i] = Math.max(a, b);
        }
        return f[0];
    }
}
