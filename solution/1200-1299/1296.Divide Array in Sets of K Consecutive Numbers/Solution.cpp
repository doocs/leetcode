class Solution {
public:
    bool isPossibleDivide(vector<int>& nums, int k) {
        if (nums.size() % k) {
            return false;
        }
        ranges::sort(nums);
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        for (int x : nums) {
            if (cnt.contains(x)) {
                for (int y = x; y < x + k; ++y) {
                    if (!cnt.contains(y)) {
                        return false;
                    }
                    if (--cnt[y] == 0) {
                        cnt.erase(y);
                    }
                }
            }
        }
        return true;
    }
};
