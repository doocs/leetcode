/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder = function (matrix) {
    const ans = [];
    if (matrix.length == 0 || matrix[0].length == 0) {
        return ans;
    }
    const m = matrix.length;
    const n = matrix[0].length;
    let [top, bottom, left, right] = [0, m - 1, 0, n - 1];
    while (top <= bottom && left <= right) {
        for (let j = left; j <= right; ++j) {
            ans.push(matrix[top][j]);
        }
        for (let i = top + 1; i <= bottom; ++i) {
            ans.push(matrix[i][right]);
        }
        if (left < right && top < bottom) {
            for (let j = right - 1; j >= left; --j) {
                ans.push(matrix[bottom][j]);
            }
            for (let i = bottom - 1; i > top; --i) {
                ans.push(matrix[i][left]);
            }
        }
        [top, bottom, left, right] = [top + 1, bottom - 1, left + 1, right - 1];
    }
    return ans;
};
