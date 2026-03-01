class Solution {
    public int minimumOR(int[][] grid) {
        int mx = 0;
        for (int[] row : grid) {
            for (int x : row) {
                mx = Math.max(mx, x);
            }
        }

        int m = 32 - Integer.numberOfLeadingZeros(mx);
        int ans = 0;

        for (int i = m - 1; i >= 0; i--) {
            int mask = ans | ((1 << i) - 1);
            for (int[] row : grid) {
                boolean found = false;
                for (int x : row) {
                    if ((x | mask) == mask) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    ans |= 1 << i;
                    break;
                }
            }
        }

        return ans;
    }
}
