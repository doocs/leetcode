func countPaths(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	mod := int(1e9) + 7
	ans := 0
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(int, int) int
	dfs = func(i, j int) int {
		if f[i][j] > 0 {
			return f[i][j]
		}
		res := 1
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > grid[i][j] {
				res = (res + dfs(x, y)) % mod
			}
		}
		f[i][j] = res
		return res
	}
	for i, row := range grid {
		for j := range row {
			ans = (ans + dfs(i, j)) % mod
		}
	}
	return ans
}