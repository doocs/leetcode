func luckyNumbers (matrix [][]int) []int {
    m, n := len(matrix), len(matrix[0])
    rows, cols := make([]int, m), make([]int, n)
    for i := range rows {
        rows[i] = math.MaxInt32
    }
    for i, row := range matrix {
        for j, v := range row {
            rows[i] = min(rows[i], v)
            cols[j] = max(cols[j], v)
        }
    }
    var ans []int
    for i, row := range matrix {
        for j, v := range row {
            if rows[i] == cols[j] {
                ans = append(ans, v)
            }
        }
    }
    return ans
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}