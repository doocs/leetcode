func possibleToStamp(grid [][]int, stampHeight int, stampWidth int) bool {
	m, n := len(grid), len(grid[0])
	s := make([][]int, m+1)
	d := make([][]int, m+1)
	cnt := make([][]int, m+1)
	for i := range s {
		s[i] = make([]int, n+1)
		d[i] = make([]int, n+1)
		cnt[i] = make([]int, n+1)
	}
	for i, row := range grid {
		for j, v := range row {
			s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j] + v
		}
	}
	for i, row := range grid {
		for j, v := range row {
			if v == 0 {
				x, y := i+stampHeight, j+stampWidth
				if x <= m && y <= n && s[x][y]-s[i][y]-s[x][j]+s[i][j] == 0 {
					d[i][j]++
					d[i][y]--
					d[x][j]--
					d[x][y]++
				}
			}
		}
	}
	for i, row := range grid {
		for j, v := range row {
			cnt[i+1][j+1] = cnt[i+1][j] + cnt[i][j+1] - cnt[i][j] + d[i][j]
			if v == 0 && cnt[i+1][j+1] == 0 {
				return false
			}
		}
	}
	return true
}