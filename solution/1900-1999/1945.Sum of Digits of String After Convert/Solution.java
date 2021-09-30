class Solution {
    public int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c - 'a' + 1);
        }
        s = sb.toString();
        while (k-- > 0) {
            int t = 0;
            for (int i = 0; i < s.length(); ++i) {
                t += s.charAt(i) - '0';
            }
            s = String.valueOf(t);
        }
        return Integer.parseInt(s);
    }
}