class Solution {
    public int maximumGap(String skill, String station) {
        int n = skill.length();
        int m = station.length();

        int[] suf = new int[n];
        int j = m - 1;

        for (int i = n - 1; i > 0; i--) {
            while (station.charAt(j) != skill.charAt(i)) {
                j--;
            }

            suf[i] = j;
            j--;
        }

        int ans = 0;
        int pre = 0;

        for (int i = 0; i < n - 1; i++) {
            while (station.charAt(pre) != skill.charAt(i)) {
                pre++;
            }

            ans = Math.max(ans, suf[i + 1] - pre);
            pre++;
        }

        return ans;
    }
}