class Solution {
    public int countBalls(int lowLimit, int highLimit) {
        int[] counter = new int[60];
        int ans = 0;
        for (int i = lowLimit; i <= highLimit; ++i) {
            int s = 0;
            int j = i;
            while (j > 0) {
                s += (j % 10);
                j /= 10;
            }
            ++counter[s];
            ans = Math.max(ans, counter[s]);
        }
        return ans;
    }
}