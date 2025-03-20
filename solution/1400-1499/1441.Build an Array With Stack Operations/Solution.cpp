class Solution {
public:
    vector<string> buildArray(vector<int>& target, int n) {
        vector<string> ans;
        int cur = 1;
        for (int x : target) {
            while (cur < x) {
                ans.push_back("Push");
                ans.push_back("Pop");
                ++cur;
            }
            ans.push_back("Push");
            ++cur;
        }
        return ans;
    }
};
