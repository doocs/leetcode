class Solution {
public:
    long long numberOfSubarrays(vector<int>& nums) {
        vector<pair<int, int>> stk;
        long long ans = 0;
        for (int x : nums) {
            while (!stk.empty() && stk.back().first < x) {
                stk.pop_back();
            }
            if (stk.empty() || stk.back().first > x) {
                stk.push_back(make_pair(x, 1));
            } else {
                stk.back().second++;
            }
            ans += stk.back().second;
        }
        return ans;
    }
};