class Solution {
public:
    string maxSumOfSquares(int num, int sum) {
        if (num * 9 < sum) {
            return "";
        }
        int k = sum / 9, s = sum % 9;
        string ans(k, '9');
        if (s > 0) {
            ans += char('0' + s);
        }
        ans += string(num - ans.size(), '0');
        return ans;
    }
};
