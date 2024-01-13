class Solution {
public:
    int splitNum(int num) {
        string s = to_string(num);
        sort(s.begin(), s.end());
        int ans[2]{};
        for (int i = 0; i < s.size(); ++i) {
            ans[i & 1] = ans[i & 1] * 10 + s[i] - '0';
        }
        return ans[0] + ans[1];
    }
};