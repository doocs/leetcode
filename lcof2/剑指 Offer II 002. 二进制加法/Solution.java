class Solution {
    public String addBinary(String a, String b) {
        int x = a.length() - 1, y = b.length() - 1;
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        while (x >= 0 || y >= 0) {
            if (x >= 0) {
                if (a.charAt(x) == '1') {
                    carry += 1;
                }
                x--;
            }
            if (y >= 0) {
                if (b.charAt(y) == '1') {
                    carry += 1;
                }
                y--;
            }
            builder.append((char) ((carry & 1) + '0'));
            carry >>= 1;
        }
        if (carry == 1) {
            builder.append('1');
        }
        return builder.reverse().toString();
    }
}
