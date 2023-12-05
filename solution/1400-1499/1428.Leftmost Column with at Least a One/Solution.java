/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> e = binaryMatrix.dimensions();
        int m = e.get(0), n = e.get(1);
        int ans = n;
        for (int i = 0; i < m; ++i) {
            int l = 0, r = n;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (binaryMatrix.get(i, mid) == 1) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans = Math.min(ans, l);
        }
        return ans >= n ? -1 : ans;
    }
}