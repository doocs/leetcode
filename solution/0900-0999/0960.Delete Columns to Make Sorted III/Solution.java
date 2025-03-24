class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs[0].length();
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                boolean ok = true;
                for (String s : strs) {
                    if (s.charAt(j) > s.charAt(i)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        return n - Arrays.stream(f).max().getAsInt();
    }
}
