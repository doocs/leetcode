func possibleToStamp(grid [][]int, stampHeight int, stampWidth int) bool {
	m, n := len(grid), len(grid[0])
	s := make([][]int, m+1)
	for i := range s {
		s[i] = make([]int, n+1)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			s[i][j] = s[i-1][j] + s[i][j-1] - s[i-1][j-1] + grid[i-1][j-1]
		}
	}

	d := make([][]int, m+2)
	for i := range d {
		d[i] = make([]int, n+2)
	}

	for i := 1; i+stampHeight-1 <= m; i++ {
		for j := 1; j+stampWidth-1 <= n; j++ {
			x, y := i+stampHeight-1, j+stampWidth-1
			if s[x][y]-s[x][j-1]-s[i-1][y]+s[i-1][j-1] == 0 {
				d[i][j]++
				d[i][y+1]--
				d[x+1][j]--
				d[x+1][y+1]++
			}
		}
	}

	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			d[i][j] += d[i-1][j] + d[i][j-1] - d[i-1][j-1]
			if grid[i-1][j-1] == 0 && d[i][j] == 0 {
				return false
			}
		}
	}
	return true
}