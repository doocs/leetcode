class Solution {
    public int nextGreaterElement(int n) {
        if (n < 12) {
            return -1;
        }
        char[] cs = String.valueOf(n).toCharArray();
        int i = cs.length - 2;
        while (i >= 0 && cs[i] >= cs[i + 1]) {
            --i;
        }
        if (i < 0) {
            return -1;
        }
        int j = cs.length - 1;
        while (cs[i] >= cs[j]) {
            --j;
        }
        swap(cs, i, j);
        reverse(cs, i + 1, cs.length - 1);
        long res = 0;
        for (char c : cs) {
            res = res * 10 + c - '0';
        }
        return res <= Integer.MAX_VALUE ? (int) res : -1;
    }

    private void reverse(char[] cs, int i, int j) {
        while (i < j) {
            swap(cs, i++, j--);
        }
    }

    private void swap(char[] cs, int i, int j) {
        char tmp = cs[i];
        cs[i] = cs[j];
        cs[j] = tmp;
    }
}
