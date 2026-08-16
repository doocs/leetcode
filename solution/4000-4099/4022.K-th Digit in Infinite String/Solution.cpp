class Solution {
public:
    int kthDigit(long long k) {
        if (k <= 9) {
            return (int) k;
        }

        k -= 9;
        long long d = 2;
        long long start = 1;
        long long size = 0;

        while (true) {
            long long cnt = 9 * (long long) pow(10, d - 2);
            size = 10 * d;

            if (k <= cnt * size) {
                break;
            }

            k -= cnt * size;
            d++;
            start *= 10;
        }

        long long b = start + (k - 1) / size;
        long long pos = (k - 1) % size;

        long long i = pos / d;

        long long num;
        if (b % 2 == 0) {
            num = 10 * b + i;
        } else {
            num = 10 * b + 9 - i;
        }

        return to_string(num)[pos % d] - '0';
    }
};