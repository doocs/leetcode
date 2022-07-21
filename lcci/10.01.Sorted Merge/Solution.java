class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
        int i = m - 1, j = n - 1;
        for (int k = A.length - 1; k >= 0; --k) {
            if (j < 0 || (i >= 0 && A[i] >= B[j])) {
                A[k] = A[i--];
            } else {
                A[k] = B[j--];
            }
        }
    }
}