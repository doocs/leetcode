func hasValidPath(grid [][]byte) bool {
	m, n := len(grid), len(grid[0])
	vis := make([][][]bool, m)
	for i := range vis {
		vis[i] = make([][]bool, n)
		for j := range vis[i] {
			vis[i][j] = make([]bool, m+n)
		}
	}
	var dfs func(int, int, int) bool
	dfs = func(i, j, t int) bool {
		if vis[i][j][t] {
			return false
		}
		vis[i][j][t] = true
		if grid[i][j] == '(' {
			t += 1
		} else {
			t -= 1
		}
		if t < 0 {
			return false
		}
		if i == m-1 && j == n-1 {
			return t == 0
		}
		dirs := []int{1, 0, 1}
		for k := 0; k < 2; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x < m && y < n && dfs(x, y, t) {
				return true
			}
		}
		return false
	}
	return dfs(0, 0, 0)
}