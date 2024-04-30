/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function (matrix, target) {
    const n = matrix[0].length;
    for (const row of matrix) {
        let left = 0,
            right = n;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (row[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (left != n && row[left] == target) {
            return true;
        }
    }
    return false;
};
