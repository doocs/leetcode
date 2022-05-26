/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function (matrix, target) {
    const m = matrix.length,
        n = matrix[0].length;
    let left = 0,
        right = m * n - 1;
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        const x = Math.floor(mid / n);
        const y = mid % n;
        if (matrix[x][y] <= target) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return matrix[Math.floor(left / n)][left % n] == target;
};
