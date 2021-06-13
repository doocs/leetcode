class Solution {
    public int maximumRemovals(String s, String p, int[] removable) {
        int low = 0, high = removable.length;
        while (low < high) {
            int mid = (low + high + 1) >> 1;
            if (isSubsequence(s, p, removable, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean isSubsequence(String s, String p, int[] removable, int mid) {
        int m = s.length(), n = p.length();
        int i = 0, j = 0;
        Set<Integer> ids = new HashSet<>();
        for (int k = 0; k < mid; ++k) {
            ids.add(removable[k]);
        }
        while (i < m && j < n) {
            if (!ids.contains(i) && s.charAt(i) == p.charAt(j)) {
                ++j;
            }
            ++i;
        }
        return j == n;
    }
}