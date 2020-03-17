class Solution {
    public int longestDecomposition(String text) {
        char[] cs = text.toCharArray();
        int res = 0;
        for (int i = 0, j = cs.length - 1; i <= j; ) {
            boolean flag = true;
            for (int k = 1; i + k - 1 < j - k + 1; ++k) {
                if (check(cs, i, j - k + 1, k)) {
                    res += 2;
                    i += k;
                    j -= k;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ++res;
                break;
            }
        }
        return res;
    }

    private boolean check(char[] cs, int i, int j, int k) {
        while (k-- > 0) {
            if (cs[i++] != cs[j++]) {
                return false;
            }
        }
        return true;
    }
}
