class Solution {
public:
    int numOfStrings(vector<string>& patterns, string word) {
        int ans = 0;
        for (auto& p : patterns) {
            ans += word.find(p) != string::npos;
        }
        return ans;
    }
};