class Solution {
public:
    int findNthDigit(int n) {
        int digit = 1;
        long long start = 0;
        long long count = 10;
        while (n > count) {
            n -= count;
            ++digit;
            start = start == 0 ? 10 : start * 10;
            count = 9 * start * digit;
        }
        long long num = start + n / digit;
        return to_string(num)[n % digit] - '0';
    }
};
