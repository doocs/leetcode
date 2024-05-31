class Solution {
public:
    bool isLongPressedName(string name, string typed) {
        int m = name.length(), n = typed.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (name[i] != typed[j]) {
                return false;
            }
            int x = i + 1;
            while (x < m && name[x] == name[i]) {
                ++x;
            }
            int y = j + 1;
            while (y < n && typed[y] == typed[j]) {
                ++y;
            }
            if (x - i > y - j) {
                return false;
            }
            i = x;
            j = y;
        }
        return i == m && j == n;
    }
};