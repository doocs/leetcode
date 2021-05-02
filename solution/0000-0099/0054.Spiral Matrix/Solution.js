/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder = function (matrix) {
    let m = matrix.length;
    if (m === 0) return [];
    let res = [];
    let top = 0, bottom = m - 1, left = 0, right = matrix[0].length - 1;
    while (left < right && bottom > top) {
        for (let i = left; i < right; i++) res.push(matrix[top][i]);
        for (let i = top; i < bottom; i++) res.push(matrix[i][right]);
        for (let i = right; i > left; i--) res.push(matrix[bottom][i]);
        for (let i = bottom; i > top; i--) res.push(matrix[i][left]);
        top++;
        bottom--;
        left++;
        right--;
    }
    if (left === right) {
        for (i = top; i <= bottom; i++) res.push(matrix[i][left]);
    } else if (top === bottom) {
        for (i = left; i <= right; i++) res.push(matrix[top][i]);
    }
    return res;
};