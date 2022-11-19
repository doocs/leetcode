class Solution {
    public String replaceSpaces(String S, int length) {
        char[] cs = S.toCharArray();
        int j = cs.length;
        for (int i = length - 1; i >= 0; --i) {
            if (cs[i] == ' ') {
                cs[--j] = '0';
                cs[--j] = '2';
                cs[--j] = '%';
            } else {
                cs[--j] = cs[i];
            }
        }
        return new String(cs, j, cs.length - j);
    }
}