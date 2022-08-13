class Solution {
public:
    long long smallestNumber(long long num) {
        if (num == 0) return 0;
        vector<int> cnt(10);
        bool neg = num < 0;
        num = abs(num);
        while (num) {
            cnt[num % 10]++;
            num /= 10;
        }
        long long ans = 0;
        if (neg) {
            for (int i = 9; i >= 0; --i)
                while (cnt[i]--) ans = ans * 10 + i;
            return -ans;
        }
        if (cnt[0]) {
            for (int i = 1; i < 10; ++i) {
                if (cnt[i]) {
                    ans = ans * 10 + i;
                    cnt[i]--;
                    break;
                }
            }
        }
        for (int i = 0; i < 10; ++i)
            while (cnt[i]--) ans = ans * 10 + i;
        return ans;
    }
};