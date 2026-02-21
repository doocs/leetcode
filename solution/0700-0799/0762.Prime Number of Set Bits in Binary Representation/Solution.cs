public class Solution {
    public int CountPrimeSetBits(int left, int right) {
        var primes = new HashSet<int> { 2, 3, 5, 7, 11, 13, 17, 19 };
        int ans = 0;

        for (int i = left; i <= right; ++i) {
            int bits = BitOperations.PopCount((uint)i);
            if (primes.Contains(bits)) {
                ++ans;
            }
        }

        return ans;
    }
}