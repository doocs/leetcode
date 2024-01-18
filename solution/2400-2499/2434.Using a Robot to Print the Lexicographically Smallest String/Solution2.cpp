class Solution {
public:
    string robotWithString(string s) {
        int n = s.size();
        vector<int> right(n, n - 1);
        for (int i = n - 2; i >= 0; --i) {
            right[i] = s[i] < s[right[i + 1]] ? i : right[i + 1];
        }
        string ans;
        string stk;
        for (int i = 0; i < n; ++i) {
            stk += s[i];
            while (!stk.empty() && (stk.back() <= (i > n - 2 ? 'z' + 1 : s[right[i + 1]]))) {
                ans += stk.back();
                stk.pop_back();
            }
        }
        return ans;
    }
};