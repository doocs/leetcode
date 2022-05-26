func maxKilledEnemies(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		t := 0
		for j := 0; j < n; j++ {
			if grid[i][j] == 'W' {
				t = 0
			} else if grid[i][j] == 'E' {
				t++
			}
			g[i][j] += t
		}
		t = 0
		for j := n - 1; j >= 0; j-- {
			if grid[i][j] == 'W' {
				t = 0
			} else if grid[i][j] == 'E' {
				t++
			}
			g[i][j] += t
		}
	}
	for j := 0; j < n; j++ {
		t := 0
		for i := 0; i < m; i++ {
			if grid[i][j] == 'W' {
				t = 0
			} else if grid[i][j] == 'E' {
				t++
			}
			g[i][j] += t
		}
		t = 0
		for i := m - 1; i >= 0; i-- {
			if grid[i][j] == 'W' {
				t = 0
			} else if grid[i][j] == 'E' {
				t++
			}
			g[i][j] += t
		}
	}
	ans := 0
	for i, row := range grid {
		for j, v := range row {
			if v == '0' && ans < g[i][j] {
				ans = g[i][j]
			}
		}
	}
	return ans
}