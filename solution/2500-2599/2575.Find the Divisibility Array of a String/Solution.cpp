class Solution {
public:
    vector<int> divisibilityArray(string word, int m) {
        vector<int> ans;
        long long x = 0;
        for (char& c : word) {
            x = (x * 10 + c - '0') % m;
            ans.push_back(x == 0 ? 1 : 0);
        }
        return ans;
    }
};