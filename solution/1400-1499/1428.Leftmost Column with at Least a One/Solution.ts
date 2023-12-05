/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * class BinaryMatrix {
 *      get(row: number, col: number): number {}
 *
 *      dimensions(): number[] {}
 * }
 */

function leftMostColumnWithOne(binaryMatrix: BinaryMatrix) {
    const [m, n] = binaryMatrix.dimensions();
    let ans = n;
    for (let i = 0; i < m; ++i) {
        let [l, r] = [0, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (binaryMatrix.get(i, mid) === 1) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        ans = Math.min(ans, l);
    }
    return ans >= n ? -1 : ans;
}
