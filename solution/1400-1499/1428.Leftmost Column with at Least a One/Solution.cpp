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
        vector<int> scale = binaryMatrix.dimensions();
        int rows = scale[0], cols = scale[1];
        int res = -1;
        for (int row = 0; row < rows; ++row) {
            int left = 0, right = cols - 1;
            while (left < right) {
                int mid = left + right >> 1;
                if (binaryMatrix.get(row, mid) == 1) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (binaryMatrix.get(row, left) == 1) {
                if (res == -1) {
                    res = left;
                } else {
                    res = min(res, left);
                }
            }
        }
        return res;
    }
};