class Solution {
public:
    int getLucky(string s, int k) {
        string t;
        for (char c : s) t += to_string(c - 'a' + 1);
        s = t;
        while (k--) {
            int t = 0;
            for (char c : s) t += c - '0';
            s = to_string(t);
        }
        return stoi(s);
    }
};