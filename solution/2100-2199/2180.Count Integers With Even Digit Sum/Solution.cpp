class Solution {
public:
    int countEven(int num) {
        int ans = 0;
        for (int i = 1; i <= num; ++i) {
            int t = 0;
            for (int j = i; j; j /= 10) t += j % 10;
            if (t % 2 == 0) ++ans;
        }
        return ans;
    }
};