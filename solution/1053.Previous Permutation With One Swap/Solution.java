class Solution {
    public int[] prevPermOpt1(int[] A) {
        for (int i = A.length - 2; i >= 0; --i) {
            if (A[i] > A[i + 1]) {
                int k = i + 1;
                for (int j = k + 1; j < A.length; ++j) {
                    if (A[j] < A[i] && A[j] > A[k]) {
                        k = j;
                    }
                }
                int t = A[i];
                A[i] = A[k];
                A[k] = t;
                return A;
            }
        }
        return A;
    }
}
