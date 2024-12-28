/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function (matrix, target) {
    const n = matrix[0].length;
    for (const row of matrix) {
        const j = _.sortedIndex(row, target);
        if (j < n && row[j] == target) {
            return true;
        }
    }
    return false;
};
