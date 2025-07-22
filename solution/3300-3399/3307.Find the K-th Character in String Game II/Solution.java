class Solution {
    public char kthCharacter(long k, int[] operations) {
        long n = 1;
        int i = 0;
        while (n < k) {
            n *= 2;
            ++i;
        }
        int d = 0;
        while (n > 1) {
            if (k > n / 2) {
                k -= n / 2;
                d += operations[i - 1];
            }
            n /= 2;
            --i;
        }
        return (char) ('a' + (d % 26));
    }
}
