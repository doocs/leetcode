class Solution {
public:
    bool canChoose(vector<vector<int>>& groups, vector<int>& nums) {
        auto check = [&](vector<int>& a, vector<int>& b, int j) {
            int m = a.size(), n = b.size();
            int i = 0;
            for (; i < m && j < n; ++i, ++j) {
                if (a[i] != b[j]) {
                    return false;
                }
            }
            return i == m;
        };
        int n = groups.size(), m = nums.size();
        int i = 0;
        for (int j = 0; i < n && j < m;) {
            if (check(groups[i], nums, j)) {
                j += groups[i].size();
                ++i;
            } else {
                ++j;
            }
        }
        return i == n;
    }
};