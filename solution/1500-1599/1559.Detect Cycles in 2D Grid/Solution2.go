func containsCycle(grid [][]byte) bool {
	m, n := len(grid), len(grid[0])
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(x, y, px, py int) bool
	dfs = func(x, y, px, py int) bool {
		vis[x][y] = true
		for k := 0; k < 4; k++ {
			nx, ny := x+dirs[k], y+dirs[k+1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n {
				if grid[nx][ny] != grid[x][y] || (nx == px && ny == py) {
					continue
				}
				if vis[nx][ny] || dfs(nx, ny, x, y) {
					return true
				}
			}
		}
		return false
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if !vis[i][j] && dfs(i, j, -1, -1) {
				return true
			}
		}
	}
	return false
}
