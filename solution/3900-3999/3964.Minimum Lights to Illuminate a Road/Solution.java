class Solution {
    public int minLights(int[] lights) {
        int n = lights.length;
        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            int v = lights[i];
            if (v > 0) {
                int l = Math.max(0, i - v);
                int r = Math.min(n - 1, i + v);
                d[l]++;
                if (r + 1 < n) {
                    d[r + 1]--;
                }
            }
        }

        int s = 0, cnt = 0, ans = 0;
        for (int x : d) {
            s += x;
            if (s == 0) {
                cnt++;
            } else {
                ans += (cnt + 2) / 3;
                cnt = 0;
            }
        }

        ans += (cnt + 2) / 3;
        return ans;
    }
}