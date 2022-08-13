class Solution {
public:
    vector<string> buildArray(vector<int>& target, int n) {
        vector<string> ans;
        int cur = 1;
        for (int t : target) {
            for (int i = cur; i <= n; ++i) {
                ans.push_back("Push");
                if (t == i) {
                    cur = i + 1;
                    break;
                }
                ans.push_back("Pop");
            }
        }
        return ans;
    }
};