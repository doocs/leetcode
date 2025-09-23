public class Solution {
    public int MakeTheIntegerZero(int num1, int num2) {
        long a = num1, b = num2;
        for (long k = 1; ; ++k) {
            long x = a - k * b;
            if (x < 0) {
                break;
            }
            if (BitOperations.PopCount((ulong)x) <= k && k <= x) {
                return (int)k;
            }
        }
        return -1;
    }
}
