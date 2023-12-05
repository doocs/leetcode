/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 *  struct BinaryMatrix;
 *  impl BinaryMatrix {
 *     fn get(row: i32, col: i32) -> i32;
 *     fn dimensions() -> Vec<i32>;
 * };
 */

impl Solution {
    pub fn left_most_column_with_one(binaryMatrix: &BinaryMatrix) -> i32 {
        let e = binaryMatrix.dimensions();
        let m = e[0] as usize;
        let n = e[1] as usize;
        let mut ans = n;

        for i in 0..m {
            let (mut l, mut r) = (0, n);
            while l < r {
                let mid = (l + r) / 2;
                if binaryMatrix.get(i as i32, mid as i32) == 1 {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans = ans.min(l);
        }

        if ans >= n {
            -1
        } else {
            ans as i32
        }
    }
}
