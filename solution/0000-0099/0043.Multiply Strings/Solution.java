class Solution {
    public String multiply(String num1, String num2) {
        if (Objects.equals(num1, "0") || Objects.equals(num2, "0")) {
            return "0";
        }
        int n1 = num1.length(), n2 = num2.length();
        String ans = "";
        for (int i = 0; i < n1; ++i) {
            int a = num1.charAt(n1 - i - 1) - '0';
            String t = "";
            for (int j = 0; j < n2; ++j) {
                int b = num2.charAt(n2 - j - 1) - '0';
                StringBuilder sb = new StringBuilder(String.valueOf(a * b));
                for (int k = 0; k < j; ++k) {
                    sb.append(0);
                }
                t = add(t, sb.toString());
            }
            StringBuilder sb = new StringBuilder(t);
            for (int k = 0; k < i; ++k) {
                sb.append(0);
            }
            ans = add(ans, sb.toString());
        }
        return ans;
    }

    private String add(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        StringBuilder res = new StringBuilder();
        for (int i = 0, carry = 0; i < Math.max(n1, n2) || carry > 0; ++i) {
            int a = i < n1 ? (s1.charAt(n1 - i - 1) - '0') : 0;
            int b = i < n2 ? (s2.charAt(n2 - i - 1) - '0') : 0;
            int s = a + b + carry;
            carry = s / 10;
            res.append(s % 10);
        }
        return res.reverse().toString();
    }
}