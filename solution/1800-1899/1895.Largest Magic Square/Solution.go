func largestMagicSquare(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	rowsum := make([][]int, m+1)
	colsum := make([][]int, m+1)
	for i := 0; i <= m; i++ {
		rowsum[i] = make([]int, n+1)
		colsum[i] = make([]int, n+1)
	}
	for i := 1; i < m+1; i++ {
		for j := 1; j < n+1; j++ {
			rowsum[i][j] = rowsum[i][j-1] + grid[i-1][j-1]
			colsum[i][j] = colsum[i-1][j] + grid[i-1][j-1]
		}
	}
	for k := min(m, n); k > 1; k-- {
		for i := 0; i+k-1 < m; i++ {
			for j := 0; j+k-1 < n; j++ {
				i2, j2 := i+k-1, j+k-1
				if check(grid, rowsum, colsum, i, j, i2, j2) {
					return k
				}
			}
		}
	}
	return 1
}

func check(grid, rowsum, colsum [][]int, x1, y1, x2, y2 int) bool {
	val := rowsum[x1+1][y2+1] - rowsum[x1+1][y1]
	for i := x1 + 1; i < x2+1; i++ {
		if rowsum[i+1][y2+1]-rowsum[i+1][y1] != val {
			return false
		}
	}
	for j := y1; j < y2+1; j++ {
		if colsum[x2+1][j+1]-colsum[x1][j+1] != val {
			return false
		}
	}
	s := 0
	for i, j := x1, y1; i <= x2; i, j = i+1, j+1 {
		s += grid[i][j]
	}
	if s != val {
		return false
	}
	s = 0
	for i, j := x1, y2; i <= x2; i, j = i+1, j-1 {
		s += grid[i][j]
	}
	if s != val {
		return false
	}
	return true
}

func min(a, b int) int {
	if a > b {
		return a
	}
	return b
}