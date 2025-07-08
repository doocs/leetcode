class Solution {
public:
    string concatHex36(int n) {
        int x = n * n;
        int y = n * n * n;
        return f(x, 16) + f(y, 36);
    }

private:
    string f(int x, int k) {
        string res;
        while (x > 0) {
            int v = x % k;
            if (v <= 9) {
                res += char('0' + v);
            } else {
                res += char('A' + v - 10);
            }
            x /= k;
        }
        reverse(res.begin(), res.end());
        return res;
    }
};
