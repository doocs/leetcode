func largest1BorderedSquare(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	down := make([][]int, m)
	right := make([][]int, m)
	for i := range down {
		down[i] = make([]int, n)
		right[i] = make([]int, n)
	}
	for i := m - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if grid[i][j] == 1 {
				down[i][j], right[i][j] = 1, 1
				if i+1 < m {
					down[i][j] += down[i+1][j]
				}
				if j+1 < n {
					right[i][j] += right[i][j+1]
				}
			}
		}
	}
	for k := min(m, n); k > 0; k-- {
		for i := 0; i <= m-k; i++ {
			for j := 0; j <= n-k; j++ {
				if down[i][j] >= k && right[i][j] >= k && right[i+k-1][j] >= k && down[i][j+k-1] >= k {
					return k * k
				}
			}
		}
	}
	return 0
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}