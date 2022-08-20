class Solution {
public:
    int magicalString(int n) {
        string s = "1221121";
        int i = 5;
        while (s.size() < n) {
            if (s[i] == '1') {
                s += s.back() == '1' ? "2" : "1";
            } else {
                s += s.back() == '1' ? "22" : "11";
            }
            ++i;
        }
        return count(s.begin(), s.begin() + n, '1');
    }
};