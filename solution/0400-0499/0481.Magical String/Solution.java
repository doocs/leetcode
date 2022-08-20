class Solution {
    public int magicalString(int n) {
        StringBuilder s = new StringBuilder("1221121");
        int i = 5;
        while (s.length() < n) {
            char c = s.charAt(s.length() - 1);
            if (s.charAt(i) == '1') {
                if (c == '1') {
                    s.append('2');
                } else {
                    s.append('1');
                }
            } else {
                if (c == '1') {
                    s.append("22");
                } else {
                    s.append("11");
                }
            }
            ++i;
        }
        int ans = 0;
        for (i = 0; i < n; ++i) {
            if (s.charAt(i) == '1') {
                ++ans;
            }
        }
        return ans;
    }
}