class Solution {
public:
    vector<int> goodIndices(string s) {
        vector<int> ans;
        for (int i = 0; i < s.size(); i++) {
            string t = to_string(i);
            int k = t.size();
            if (s.substr(i + 1 - k, k) == t) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
