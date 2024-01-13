/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * class BinaryMatrix {
 *   public:
 *     int get(int row, int col);
 *     vector<int> dimensions();
 * };
 */

class Solution {
public:
    int leftMostColumnWithOne(BinaryMatrix& binaryMatrix) {
        auto e = binaryMatrix.dimensions();
        int m = e[0], n = e[1];
        int ans = n;
        for (int i = 0; i < m; ++i) {
            int l = 0, r = n;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (binaryMatrix.get(i, mid)) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans = min(ans, l);
        }
        return ans >= n ? -1 : ans;
    }
};