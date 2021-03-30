class Solution {
    public int search(int[] A, int target) {
        if (A == null || A.length == 0) return -1;
        int low = 0,high = A.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (target < A[mid]) {
                if (A[mid] >= A[high] && target < A[low]) low = mid + 1;
                else high = mid - 1;
            } else if (target > A[mid]) {
                if (A[low] >= A[mid] && target > A[high]) high = mid - 1;
                else low = mid + 1;
            } else return mid;
        }
        return -1;
    }
}