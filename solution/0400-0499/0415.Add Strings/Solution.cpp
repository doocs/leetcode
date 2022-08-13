class Solution {
public:
    string addStrings(string num1, string num2) {
        string ans;
        int i = num1.size() - 1, j = num2.size() - 1, carry = 0;
        for (; i >= 0 || j >= 0 || carry; --i, --j) {
            carry += (i < 0 ? 0 : num1[i] - '0') + (j < 0 ? 0 : num2[j] - '0');
            ans += to_string(carry % 10);
            carry /= 10;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};