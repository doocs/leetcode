class Solution {
public:
    vector<vector<int>> findContinuousSequence(int target) {
        vector<vector<int>> ans;
        int l = 1, r = 2;
        while (l < r) {
            int s = (l + r) * (r - l + 1) / 2;
            if (s == target) {
                vector<int> t(r - l + 1);
                iota(t.begin(), t.end(), l);
                ans.emplace_back(t);
                ++l;
            } else if (s < target) {
                ++r;
            } else {
                ++l;
            }
        }
        return ans;
    }
};