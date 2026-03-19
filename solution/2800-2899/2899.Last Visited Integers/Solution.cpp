class Solution {
public:
    vector<int> lastVisitedIntegers(vector<int>& nums) {
        vector<int> seen;
        vector<int> ans;
        int k = 0;

        for (int x : nums) {
            if (x == -1) {
                if (++k > seen.size()) {
                    ans.push_back(-1);
                } else {
                    ans.push_back(seen[seen.size() - k]);
                }
            } else {
                k = 0;
                seen.push_back(x);
            }
        }

        return ans;
    }
};
