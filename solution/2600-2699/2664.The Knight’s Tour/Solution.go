func tourOfKnight(m int, n int, r int, c int) [][]int {
	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = -1
		}
	}
	g[r][c] = 0
	ok := false
	var dfs func(i, j int)
	dfs = func(i, j int) {
		if g[i][j] == m*n-1 {
			ok = true
			return
		}
		dirs := []int{-2, -1, 2, 1, -2, 1, 2, -1, -2}
		for k := 0; k < 8; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && g[x][y] == -1 {
				g[x][y] = g[i][j] + 1
				dfs(x, y)
				if ok {
					return
				}
				g[x][y] = -1
			}
		}
	}
	dfs(r, c)
	return g
}