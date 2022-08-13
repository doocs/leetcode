class Solution {
public:
    string makeFancyString(string s) {
        string ans = "";
        for (char& c : s) {
            int n = ans.size();
            if (n > 1 && ans[n - 1] == c && ans[n - 2] == c) continue;
            ans.push_back(c);
        }
        return ans;
    }
};