class Solution {
public:
    vector<int> selfDividingNumbers(int left, int right) {
        auto check = [&](int x) -> bool {
            for (int y = x; y; y /= 10) {
                if (y % 10 == 0 || x % (y % 10)) {
                    return false;
                }
            }
            return true;
        };
        vector<int> ans;
        for (int x = left; x <= right; ++x) {
            if (check(x)) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
