class Solution {
public:
    int compareVersion(string version1, string version2) {
        for (int i = 0, j = 0; i < version1.size() || j < version2.size(); ++i, ++j) {
            int a = 0, b = 0;
            while (i < version1.size() && version1[i] != '.')
                a = a * 10 + version1[i++] - '0';
            while (j < version2.size() && version2[j] != '.')
                b = b * 10 + version2[j++] - '0';
            if (a != b)
                return a < b ? -1 : 1;
        }
        return 0;
    }
};