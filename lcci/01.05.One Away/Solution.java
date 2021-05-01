class Solution {
    public boolean oneEditAway(String first, String second) {
        int n1 = first.length(), n2 = second.length();
        int diff = n1 - n2;
        if (Math.abs(diff) > 1) {
            return false;
        }
        int op = 1;
        for (int i = 0, j = 0; i < n1 && j < n2; ++i, ++j) {
            boolean notSame = first.charAt(i) != second.charAt(j);
            if (notSame) {
                if (diff == 1) {
                    // --j, ++i, ++j => ++i
                    --j;
                } else if (diff == -1) {
                    // --i, ++i, ++j => ++j
                    --i;
                }
                --op;
            }
            if (op < 0) {
                return false;
            }
        }
        return true;
    }
}