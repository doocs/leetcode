func luckyNumbers(matrix [][]int) (ans []int) {
	m, n := len(matrix), len(matrix[0])
	rows, cols := make([]int, m), make([]int, n)
	for i := range rows {
		rows[i] = 1 << 30
	}
	for i, row := range matrix {
		for j, x := range row {
			rows[i] = min(rows[i], x)
			cols[j] = max(cols[j], x)
		}
	}
	for i, row := range matrix {
		for j, x := range row {
			if rows[i] == cols[j] {
				ans = append(ans, x)
			}
		}
	}
	return
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