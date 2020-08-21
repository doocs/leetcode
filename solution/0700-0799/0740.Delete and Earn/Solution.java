public class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] sums = new int[10010];
        int[] select = new int[10010];
        int[] nonSelect = new int[10010];

        int maxV = 0;
        for (int x : nums) {
            sums[x] += x;
            maxV = Math.max(maxV, x);
        }

        for (int i = 1; i <= maxV; i++) {
            select[i] = nonSelect[i - 1] + sums[i];
            nonSelect[i] = Math.max(select[i - 1], nonSelect[i - 1]);
        }
        return Math.max(select[maxV], nonSelect[maxV]);
    }
}
