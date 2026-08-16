class Solution {
    public int minOperations(String s) {
        int n = s.length();
        int ans = Integer.MAX_VALUE;

        for (int k = 0; k < n; k++) {
            int t = k;
            int i = 0, j = n - 1;

            while (i < j) {
                int x = s.charAt((i + k) % n) - 'a';
                int y = s.charAt((j + k) % n) - 'a';

                int d = Math.abs(x - y);
                t += Math.min(d, 26 - d);

                i++;
                j--;
            }

            ans = Math.min(ans, t);
        }

        return ans;
    }
}