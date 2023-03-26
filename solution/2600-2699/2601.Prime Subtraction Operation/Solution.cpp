class Solution {
public:
    bool primeSubOperation(vector<int>& nums) {
        vector<int> p;
        for (int i = 2; i <= 1000; ++i) {
            bool ok = true;
            for (int j : p) {
                if (i % j == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                p.push_back(i);
            }
        }
        int n = nums.size();
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) {
                continue;
            }
            int j = upper_bound(p.begin(), p.end(), nums[i] - nums[i + 1]) - p.begin();
            if (j == p.size() || p[j] >= nums[i]) {
                return false;
            }
            nums[i] -= p[j];
        }
        return true;
    }
};