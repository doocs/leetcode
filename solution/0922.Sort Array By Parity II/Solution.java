class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int j = 1, length = A.length;
        for (int i = 0; i < length; i += 2) {
            if ((A[i] & 1) != 0) {
                while ((A[j] & 1) != 0)  j += 2;

                // Swap A[i] and A[j]
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        return A;
    }
}