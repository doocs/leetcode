class Solution {
    public String addStrings(String num1, String num2) {
        int n1 = num1.length() - 1, n2 = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (n1 >= 0 || n2 >= 0 || carry > 0) {
            carry += (n1 < 0 ? 0 : num1.charAt(n1--) - '0') + (n2 < 0 ? 0 : num2.charAt(n2--) - '0');
            sb.append(carry % 10);
            carry /= 10;
        }
        return sb.reverse().toString();
    }
}