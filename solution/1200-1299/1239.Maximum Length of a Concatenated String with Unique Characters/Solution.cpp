class Solution {
public:
    int maxLength(vector<string>& arr) {
        int ans = 0;
        vector<int> masks = {0};
        for (auto& s : arr) {
            int mask = 0;
            for (auto& c : s) {
                int i = c - 'a';
                if (mask >> i & 1) {
                    mask = 0;
                    break;
                }
                mask |= 1 << i;
            }
            if (mask == 0) {
                continue;
            }
            int n = masks.size();
            for (int i = 0; i < n; ++i) {
                int m = masks[i];
                if ((m & mask) == 0) {
                    masks.push_back(m | mask);
                    ans = max(ans, __builtin_popcount(m | mask));
                }
            }
        }
        return ans;
    }
};