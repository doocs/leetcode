class Solution {
    public int numSteps(String s) {
        boolean carry = false;
        int ans = 0;
        for (int i = s.length() - 1; i > 0; --i) {
            char c = s.charAt(i);
            if (carry) {
                if (c == '0') {
                    c = '1';
                    carry = false;
                } else {
                    c = '0';
                }
            }
            if (c == '1') {
                ++ans;
                carry = true;
            }
            ++ans;
        }
        if (carry) {
            ++ans;
        }
        return ans;
    }
}