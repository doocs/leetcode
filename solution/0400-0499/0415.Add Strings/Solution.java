class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder ans = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        for (; i >= 0 || j >= 0 || carry > 0; --i, --j) {
            carry += (i < 0 ? 0 : num1.charAt(i) - '0') + (j < 0 ? 0 : num2.charAt(j) - '0');
            ans.append(carry % 10);
            carry /= 10;
        }
        return ans.reverse().toString();
    }
}