class Solution {
public:
    bool isIsomorphic(string s, string t) {
        vector<int> d1(256);
        vector<int> d2(256);
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            char a = s[i], b = t[i];
            if (d1[a] != d2[b]) return false;
            d1[a] = d2[b] = i + 1;
        }
        return true;
    }
};