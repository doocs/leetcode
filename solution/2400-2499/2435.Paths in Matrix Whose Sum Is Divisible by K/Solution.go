func numberOfPaths(grid [][]int, k int) int {
	m, n := len(grid), len(grid[0])
	var mod int = 1e9 + 7
	f := make([][][]int, m)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, k)
			for x := 0; x < k; x++ {
				f[i][j][x] = -1
			}
		}
	}
	var dfs func(i, j, s int) int
	dfs = func(i, j, s int) int {
		if i < 0 || i >= m || j < 0 || j >= n {
			return 0
		}
		s = (s + grid[i][j]) % k
		if i == m-1 && j == n-1 {
			if s == 0 {
				return 1
			}
			return 0
		}
		if f[i][j][s] != -1 {
			return f[i][j][s]
		}
		ans := dfs(i+1, j, s) + dfs(i, j+1, s)
		ans %= mod
		f[i][j][s] = ans
		return ans
	}
	return dfs(0, 0, 0)
}