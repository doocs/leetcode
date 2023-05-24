class Solution {
public:
    int sumFourDivisors(vector<int>& nums) {
        int ans = 0;
        for (int x : nums) {
            ans += f(x);
        }
        return ans;
    }

    int f(int x) {
        int cnt = 2, s = x + 1;
        for (int i = 2; i <= x / i; ++i) {
            if (x % i == 0) {
                ++cnt;
                s += i;
                if (i * i != x) {
                    ++cnt;
                    s += x / i;
                }
            }
        }
        return cnt == 4 ? s : 0;
    }
};