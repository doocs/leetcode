class Solution {
public:
    vector<string> buildArray(vector<int>& target, int n) {
        int cur = 0;
        vector<string> ans;
        for (int& v : target) {
            while (++cur < v) {
                ans.emplace_back("Push");
                ans.emplace_back("Pop");
            }
            ans.emplace_back("Push");
        }
        return ans;
    }
};