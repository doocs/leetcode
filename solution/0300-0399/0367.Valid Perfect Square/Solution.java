class Solution {
    public boolean isPerfectSquare(int num) {
        int l = 1, r = num;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (1L * mid * mid >= num) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l * l == num;
    }
}