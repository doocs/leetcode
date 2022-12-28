class Solution {
public:
    int compareVersion(string version1, string version2) {
        int m = version1.size(), n = version2.size();
        for (int i = 0, j = 0; i < m || j < n; ++i, ++j) {
            int a = 0, b = 0;
            while (i < m && version1[i] != '.') {
                a = a * 10 + (version1[i++] - '0');
            }
            while (j < n && version2[j] != '.') {
                b = b * 10 + (version2[j++] - '0');
            }
            if (a != b) {
                return a < b ? -1 : 1;
            }
        }
        return 0;
    }
};