class Solution {
public:
    string decodeMessage(string key, string message) {
        unordered_map<char, char> d;
        d[' '] = ' ';
        int i = 0;
        string lowcase = "abcdefghijklmnopqrstuvwxyz";
        for (char c : key) {
            if (d.count(c)) continue;
            d[c] = lowcase[i]++;
        }
        string ans;
        for (char c : message) ans.push_back(d[c]);
        return ans;
    }
};