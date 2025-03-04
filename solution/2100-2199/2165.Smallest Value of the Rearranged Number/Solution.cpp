class Solution {
public:
    long long smallestNumber(long long num) {
        bool neg = num < 0;
        num = abs(num);
        int cnt[10]{};
        while (num > 0) {
            ++cnt[num % 10];
            num /= 10;
        }
        long long ans = 0;
        if (neg) {
            for (int i = 9; i >= 0; --i) {
                while (cnt[i] > 0) {
                    ans = ans * 10 + i;
                    --cnt[i];
                }
            }
            return -ans;
        }
        if (cnt[0]) {
            for (int i = 1; i < 10; ++i) {
                if (cnt[i] > 0) {
                    --cnt[i];
                    ans = i;
                    break;
                }
            }
        }
        for (int i = 0; i < 10; ++i) {
            while (cnt[i] > 0) {
                ans = ans * 10 + i;
                --cnt[i];
            }
        }
        return ans;
    }
};
