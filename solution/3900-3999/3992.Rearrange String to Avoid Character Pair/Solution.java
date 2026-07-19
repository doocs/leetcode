class Solution {
    public String rearrangeString(String s, char x, char y) {
        char[] t = s.toCharArray();
        int i = 0;
        for (int j = 0; j < t.length; j++) {
            if (t[j] == y) {
                char tmp = t[i];
                t[i] = t[j];
                t[j] = tmp;
                i++;
            }
        }
        return new String(t);
    }
}