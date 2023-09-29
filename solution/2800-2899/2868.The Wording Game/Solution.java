class Solution {
    public boolean canAliceWin(String[] a, String[] b) {
        int i = 1, j = 0;
        boolean k = true;
        String w = a[0];
        while (true) {
            if (k) {
                if (j == b.length) {
                    return true;
                }
                if ((b[j].charAt(0) == w.charAt(0) && w.compareTo(b[j]) < 0) || b[j].charAt(0) - w.charAt(0) == 1) {
                    w = b[j];
                    k = !k;
                }
                ++j;
            } else {
                if (i == a.length) {
                    return false;
                }
                if ((a[i].charAt(0) == w.charAt(0) && w.compareTo(a[i]) < 0) || a[i].charAt(0) - w.charAt(0) == 1) {
                    w = a[i];
                    k = !k;
                }
                ++i;
            }
        }
    }
}