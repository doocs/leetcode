class Solution {
    public String replaceSpaces(String S, int length) {
        char[] c = S.toCharArray();
        int j = c.length;
        for (int i = length - 1; i >= 0; i--) {
            if (c[i] == ' ') {
                c[--j] = '0';
                c[--j] = '2';
                c[--j] = '%';
            } else {
                c[--j] = c[i];
            }
        }
        return new String(c, j, c.length - j);
    }
}