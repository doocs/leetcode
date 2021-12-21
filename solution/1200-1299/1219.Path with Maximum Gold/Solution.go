func getMaximumGold(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}

	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0 || vis[i][j] {
			return 0
		}
		vis[i][j] = true
		dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
		t := 0
		for _, dir := range dirs {
			t = max(t, dfs(i+dir[0], j+dir[1]))
		}
		vis[i][j] = false
		return t + grid[i][j]
	}

	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			ans = max(ans, dfs(i, j))
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}