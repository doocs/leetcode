class Solution {
    public int minSelection(int[][] availableIntervals, int[][] targetIntervals) {
        Arrays.sort(availableIntervals, (a, b) -> a[0] - b[0]);
        Arrays.sort(targetIntervals, (a, b) -> a[0] - b[0]);
        int ans = 0, cur = 0, i = 0;
        for (int[] t : targetIntervals) {
            int l = t[0], r = t[1];
            while (cur < r) {
                int uncover = Math.max(l, cur);
                int nxt = cur;
                while (i < availableIntervals.length && availableIntervals[i][0] <= uncover) {
                    nxt = Math.max(nxt, availableIntervals[i][1]);
                    i++;
                }
                if (nxt < uncover || nxt == cur) {
                    return -1;
                }
                cur = nxt;
                ans++;
            }
        }
        return ans;
    }
}