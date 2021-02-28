class Solution {
    public boolean isMonotonic(int[] A) {
        boolean increase = true, decrease = true;
        for (int i = 1, n = A.length; i < n; ++i) {
            if (!increase && !decrease) return false;
            if (A[i] < A[i - 1]) decrease = false;
            else if (A[i] > A[i - 1]) increase = false;
        }
        return increase || decrease;
    }
}