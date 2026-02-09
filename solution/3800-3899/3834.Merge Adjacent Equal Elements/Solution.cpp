class Solution {
public:
    vector<long long> mergeAdjacent(vector<int>& nums) {
        vector<long long> stk;
        for (int x : nums) {
            stk.push_back(x);
            while (stk.size() > 1 && stk.back() == stk[stk.size() - 2]) {
                long long a = stk.back();
                stk.pop_back();
                long long b = stk.back();
                stk.pop_back();
                stk.push_back(a + b);
            }
        }
        return stk;
    }
};
