class Solution {
    public int countBalls(int lowLimit, int highLimit) {
        int[] cnt = new int[50];
        for (int i = lowLimit; i <= highLimit; ++i) {
            int x = i, y = 0;
            for (; x > 0; x /= 10) {
                y += x % 10;
            }
            ++cnt[y];
        }
        return Arrays.stream(cnt).max().getAsInt();
    }
}