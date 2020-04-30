/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var findNumberIn2DArray = function(matrix, target) {
    let row = matrix.length
    let col = matrix[0].length
    function dfs(i,j) {
        if(i < 0 || j >= col) {
            return false
        }
        if(matrix[i][j] === target) return true
        else if(matrix[i][j] > target) {
            return dfs(i-1,j)
        } else {
            return dfs(i,j+1)
        }
    }
    return dfs(row-1,0)
};