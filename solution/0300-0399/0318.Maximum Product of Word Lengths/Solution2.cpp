class Solution {
public:
    int maxProduct(vector<string>& words) {
        unordered_map<int, int> mask;
        int ans = 0;
        for (auto& s : words) {
            int a = s.size();
            int x = 0;
            for (char& c : s) {
                x |= 1 << (c - 'a');
            }
            for (auto& [y, b] : mask) {
                if ((x & y) == 0) {
                    ans = max(ans, a * b);
                }
            }
            mask[x] = max(mask[x], a);
        }
        return ans;
    }
};