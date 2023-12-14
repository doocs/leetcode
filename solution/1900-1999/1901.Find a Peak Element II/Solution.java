class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int l = 0, r = mat.length - 1;
        int n = mat[0].length;
        while (l < r) {
            int mid = (l + r) >> 1;
            int j = maxPos(mat[mid]);
            if (mat[mid][j] > mat[mid + 1][j]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return new int[] {l, maxPos(mat[l])};
    }

    private int maxPos(int[] arr) {
        int j = 0;
        for (int i = 1; i < arr.length; ++i) {
            if (arr[j] < arr[i]) {
                j = i;
            }
        }
        return j;
    }
}