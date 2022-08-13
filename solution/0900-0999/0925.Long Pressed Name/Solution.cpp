class Solution {
public:
    bool isLongPressedName(string name, string typed) {
        int m = name.size(), n = typed.size();
        int i = 0, j = 0;
        for (; i < m && j < n; ++i, ++j) {
            if (name[i] != typed[j]) return false;
            int cnt1 = 0, cnt2 = 0;
            char c = name[i];
            while (i + 1 < m && name[i + 1] == c) {
                ++i;
                ++cnt1;
            }
            while (j + 1 < n && typed[j + 1] == c) {
                ++j;
                ++cnt2;
            }
            if (cnt1 > cnt2) return false;
        }
        return i == m && j == n;
    }
};