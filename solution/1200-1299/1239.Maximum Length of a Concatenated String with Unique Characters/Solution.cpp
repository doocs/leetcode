class Solution {
public:
    int maxLength(vector<string>& arr) {
        vector<int> s = {0};
        int ans = 0;
        for (const string& t : arr) {
            int x = 0;
            for (char c : t) {
                int b = c - 'a';
                if ((x >> b & 1) == 1) {
                    x = 0;
                    break;
                }
                x |= 1 << b;
            }
            if (x > 0) {
                for (int i = s.size() - 1; i >= 0; --i) {
                    int y = s[i];
                    if ((x & y) == 0) {
                        s.push_back(x | y);
                        ans = max(ans, __builtin_popcount(x | y));
                    }
                }
            }
        }
        return ans;
    }
};
