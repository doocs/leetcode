/**
 * // This is BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * class BinaryMatrix {
 *     public int Get(int row, int col) {}
 *     public IList<int> Dimensions() {}
 * }
 */

class Solution {
    public int LeftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        var e = binaryMatrix.Dimensions();
        int m = e[0], n = e[1];
        int ans = n;
        for (int i = 0; i < m; ++i) {
            int l = 0, r = n;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (binaryMatrix.Get(i, mid) == 1) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans = Math.Min(ans, l);
        }
        return ans >= n ? -1 : ans;
    }
}