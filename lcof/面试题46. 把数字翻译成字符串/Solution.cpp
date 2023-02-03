class Solution {
public:
    int translateNum(int num) {
        string s = to_string(num);
        int n = s.size();
        int a = 1, b = 1;
        for (int i = 1; i < n; ++i) {
            int c = b;
            if (s[i - 1] == '1' || (s[i - 1] == '2' && s[i] < '6')) {
                c += a;
            }
            a = b;
            b = c;
        }
        return b;
    }
};