func lastVisitedCell(grid [][]byte) []int {
	m, n := len(grid), len(grid[0])
	r, c := 0, 0
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	for {
		cur := []int{r, c}
		vis[r][c] = true
		switch grid[r][c] {
		case 'L':
			c--
		case 'R':
			c++
		case 'U':
			r--
		case 'D':
			r++
		}
		if r >= 0 && r < m && c >= 0 && c < n && !vis[r][c] {
			continue
		}
		return cur
	}
}
