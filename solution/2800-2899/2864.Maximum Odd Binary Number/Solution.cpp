class Solution {
public:
    string maximumOddBinaryNumber(string s) {
        int cnt = count_if(s.begin(), s.end(), [](char c) { return c == '1'; });
        string ans;
        for (int i = 1; i < cnt; ++i) {
            ans.push_back('1');
        }
        for (int i = 0; i < s.size() - cnt; ++i) {
            ans.push_back('0');
        }
        ans.push_back('1');
        return ans;
    }
};