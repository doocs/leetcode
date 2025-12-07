class Solution {
public:
    vector<int> sortByReflection(vector<int>& nums) {
        auto f = [](int x) {
            int y = 0;
            while (x) {
                y = (y << 1) | (x & 1);
                x >>= 1;
            }
            return y;
        };

        sort(nums.begin(), nums.end(), [&](int a, int b) {
            int fa = f(a);
            int fb = f(b);
            if (fa != fb) {
                return fa < fb;
            }
            return a < b;
        });

        return nums;
    }
};
