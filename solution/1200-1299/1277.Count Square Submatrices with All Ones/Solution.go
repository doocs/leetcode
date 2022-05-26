func countSquares(matrix [][]int) int {
	m, n, ans := len(matrix), len(matrix[0]), 0
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	for i, row := range matrix {
		for j, v := range row {
			if v == 0 {
				continue
			}
			if i == 0 || j == 0 {
				f[i][j] = 1
			} else {
				f[i][j] = min(f[i-1][j-1], min(f[i-1][j], f[i][j-1])) + 1
			}
			ans += f[i][j]
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