class Solution {
    public int[] sortArrayByParity(int[] A) {
        int i = 0, j = A.length - 1;
        while (i < j) {
            if ((A[i] & 1) > (A[j] & 1)) {
                int t = A[i];
                A[i] = A[j];
                A[j] = t;
            }
            if ((A[i] & 1) == 0) {
                ++i;
            }
            if ((A[j] & 1) == 1) {
                --j;
            }
        }
        return A;
    }
}