class Solution {
public:
    string convert(string s, int numRows) {
        if (numRows == 1) return s;
        string ans;
        int group = 2 * numRows - 2;
        for (int i = 1; i <= numRows; ++i) {
            int interval = i == numRows ? group : 2 * numRows - 2 * i;
            int idx = i - 1;
            while (idx < s.length()) {
                ans.push_back(s[idx]);
                idx += interval;
                interval = group - interval;
                if (interval == 0) interval = group;
            }
        }
        return ans;
    }
};