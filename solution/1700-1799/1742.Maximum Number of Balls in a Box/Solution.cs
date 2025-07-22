public class Solution {
    public int CountBalls(int lowLimit, int highLimit) {
        int[] cnt = new int[50];
        for (int x = lowLimit; x <= highLimit; x++) {
            int y = 0;
            int n = x;
            while (n > 0) {
                y += n % 10;
                n /= 10;
            }
            cnt[y]++;
        }
        return cnt.Max();
    }
}
