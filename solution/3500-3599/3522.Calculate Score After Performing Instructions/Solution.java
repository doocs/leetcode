class Solution {
    public long calculateScore(String[] instructions, int[] values) {
        int n = values.length;
        boolean[] vis = new boolean[n];
        long ans = 0;
        int i = 0;

        while (i >= 0 && i < n && !vis[i]) {
            vis[i] = true;
            if (instructions[i].charAt(0) == 'a') {
                ans += values[i];
                i += 1;
            } else {
                i = i + values[i];
            }
        }

        return ans;
    }
}
