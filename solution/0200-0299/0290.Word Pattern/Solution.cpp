class Solution {
public:
    bool wordPattern(string pattern, string s) {
        istringstream is(s);
        vector<string> ss;
        while (is >> s) ss.push_back(s);
        int n = pattern.size();
        if (n != ss.size()) return false;

        unordered_map<char, string> c2str;
        unordered_map<string, char> str2c;
        for (int i = 0; i < n; ++i) {
            char k = pattern[i];
            string v = ss[i];
            if (c2str.count(k) && c2str[k] != v) return false;
            if (str2c.count(v) && str2c[v] != k) return false;
            c2str[k] = v;
            str2c[v] = k;
        }
        return true;
    }
};