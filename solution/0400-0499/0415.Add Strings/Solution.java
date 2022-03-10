class Solution {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        int carry = 0;
        StringBuilder ans = new StringBuilder();
        while (i >= 0 || j >= 0 || carry > 0) {
            carry += (i < 0 ? 0 : num1.charAt(i--) - '0') + (j < 0 ? 0 : num2.charAt(j--) - '0');
            ans.append(carry % 10);
            carry /= 10;
        }
        return ans.reverse().toString();
    }
}