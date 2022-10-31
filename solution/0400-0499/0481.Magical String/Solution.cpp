class Solution {
public:
    int magicalString(int n) {
        vector<int> s = {1, 2, 2};
        int i = 2;
        while (s.size() < n) {
            int pre = s.back();
            int cur = 3 - pre;
            for (int j = 0; j < s[i]; ++j) {
                s.emplace_back(cur);
            }
            ++i;
        }
        return count(s.begin(), s.begin() + n, 1);
    }
};