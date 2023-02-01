class Solution {
public:
    string decodeMessage(string key, string message) {
        char d[128]{};
        d[' '] = ' ';
        char i = 'a';
        for (char& c : key) {
            if (!d[c]) {
                d[c] = i++;
            }
        }
        string ans;
        for (char& c : message) {
            ans += d[c];
        }
        return ans;
    }
};