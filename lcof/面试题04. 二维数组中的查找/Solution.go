func findNumberIn2DArray(matrix [][]int, target int) bool {
    if len(matrix) == 0 {
        return false
    }
    rows, cols := len(matrix), len(matrix[0])
    i, j := rows - 1, 0
    for i >= 0 && j < cols {
        if matrix[i][j] == target {
            return true
        }
        if matrix[i][j] > target {
            i--
        } else {
            j++
        }
    }
    return false 
}