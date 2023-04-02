class Solution {
    public long makeSubKSumEqual(int[] arr, int k) {
        int n = arr.length;
        if (n == k) {
            return 0;
        }
        int g = gcd(n, k);
        long ans = 0;
        for (int i = 0; i < g; ++i) {
            List<Integer> t = new ArrayList<>();
            for (int j = i; j < n; j += g) {
                t.add(arr[j]);
            }
            t.sort((a, b) -> a - b);
            int mid = t.get(t.size() >> 1);
            long s = 0;
            for (int x : t) {
                s += Math.abs(x - mid);
            }
            ans += s;
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}