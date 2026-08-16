class Solution {
    public int kthDigit(long k) {
        if (k <= 9) {
            return (int) k;
        }

        k -= 9;
        long d = 2;
        long start = 1;
        long size = 0;

        while (true) {
            long cnt = 9 * (long) Math.pow(10, d - 2);
            size = 10 * d;

            if (k <= cnt * size) {
                break;
            }

            k -= cnt * size;
            d++;
            start *= 10;
        }

        long b = start + (k - 1) / size;
        long pos = (k - 1) % size;

        long i = pos / d;

        long num = (b % 2 == 0) ? 10 * b + i : 10 * b + 9 - i;

        return String.valueOf(num).charAt((int) (pos % d)) - '0';
    }
}