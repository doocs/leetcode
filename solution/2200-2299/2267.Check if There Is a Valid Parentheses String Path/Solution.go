func hasValidPath(grid [][]byte) bool {
	m, n := len(grid), len(grid[0])
	if (m+n-1)%2 == 1 || grid[0][0] == ')' || grid[m-1][n-1] == '(' {
		return false
	}
	vis := make([][][]bool, m)
	for i := range vis {
		vis[i] = make([][]bool, n)
		for j := range vis[i] {
			vis[i][j] = make([]bool, m+n)
		}
	}
	dirs := [3]int{1, 0, 1}
	var dfs func(i, j, k int) bool
	dfs = func(i, j, k int) bool {
		if vis[i][j][k] {
			return false
		}
		vis[i][j][k] = true
		if grid[i][j] == '(' {
			k++
		} else {
			k--
		}
		if k < 0 || k > m-i+n-j {
			return false
		}
		if i == m-1 && j == n-1 {
			return k == 0
		}
		for d := 0; d < 2; d++ {
			x, y := i+dirs[d], j+dirs[d+1]
			if x >= 0 && x < m && y >= 0 && y < n && dfs(x, y, k) {
				return true
			}
		}
		return false
	}
	return dfs(0, 0, 0)
}
