func isThereAPath(grid [][]int) bool {
	m, n := len(grid), len(grid[0])
	s := m + n - 1
	if s%2 == 1 {
		return false
	}
	s >>= 1
	f := [100][100][200]int{}
	var dfs func(i, j, k int) bool
	dfs = func(i, j, k int) bool {
		if i >= m || j >= n {
			return false
		}
		k += grid[i][j]
		if f[i][j][k] != 0 {
			return f[i][j][k] == 1
		}
		f[i][j][k] = 2
		if k > s || i+j+1-k > s {
			return false
		}
		if i == m-1 && j == n-1 {
			return k == s
		}
		res := dfs(i+1, j, k) || dfs(i, j+1, k)
		if res {
			f[i][j][k] = 1
		}
		return res
	}
	return dfs(0, 0, 0)
}