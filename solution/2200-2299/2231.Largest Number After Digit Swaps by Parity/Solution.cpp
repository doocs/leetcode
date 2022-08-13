class Solution {
public:
    int largestInteger(int num) {
        vector<int> cnt(10);
        int x = num;
        while (x) {
            cnt[x % 10]++;
            x /= 10;
        }
        x = num;
        int ans = 0;
        long t = 1;
        while (x) {
            int v = x % 10;
            x /= 10;
            for (int y = 0; y < 10; ++y) {
                if (((v ^ y) & 1) == 0 && cnt[y] > 0) {
                    cnt[y]--;
                    ans += y * t;
                    t *= 10;
                    break;
                }
            }
        }
        return ans;
    }
};