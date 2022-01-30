func longestIncreasingPath(matrix [][]int) int {
	m, n := len(matrix), len(matrix[0])
	memo := make([][]int, m)
	for i := range memo {
		memo[i] = make([]int, n)
		for j := range memo[i] {
			memo[i][j] = -1
		}
	}
	ans := -1
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if memo[i][j] != -1 {
			return memo[i][j]
		}
		ans := 1
		dirs := [4][2]int{{-1, 0}, {1, 0}, {0, 1}, {0, -1}}
		for _, dir := range dirs {
			x, y := i+dir[0], j+dir[1]
			if x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j] {
				ans = max(ans, dfs(x, y)+1)
			}
		}
		memo[i][j] = ans
		return ans
	}
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