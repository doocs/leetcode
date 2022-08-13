class Solution {
public:
    string removeDigit(string number, char digit) {
        string ans = "0";
        for (int i = 0, n = number.size(); i < n; ++i) {
            char d = number[i];
            if (d == digit) {
                string t = number.substr(0, i) + number.substr(i + 1, n - i);
                if (ans < t) ans = t;
            }
        }
        return ans;
    }
};