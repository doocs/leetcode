class Solution {
public:
    int magicalString(int n) {
        vector<int> s = {1, 2, 2};
        for (int i = 2; s.size() < n; ++i) {
            int pre = s.back();
            int cur = 3 - pre;
            for (int j = 0; j < s[i]; ++j) {
                s.emplace_back(cur);
            }
        }
        return count(s.begin(), s.begin() + n, 1);
    }
};