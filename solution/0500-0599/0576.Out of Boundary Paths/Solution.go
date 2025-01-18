func findPaths(m int, n int, maxMove int, startRow int, startColumn int) int {
	f := make([][][]int, m)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, maxMove+1)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	const mod int = 1e9 + 7
	var dfs func(int, int, int) int
	dirs := [5]int{-1, 0, 1, 0, -1}
	dfs = func(i, j, k int) int {
		if i < 0 || i >= m || j < 0 || j >= n {
			if k >= 0 {
				return 1
			}
			return 0
		}
		if k <= 0 {
			return 0
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		ans := 0
		for d := 0; d < 4; d++ {
			x, y := i+dirs[d], j+dirs[d+1]
			ans = (ans + dfs(x, y, k-1)) % mod
		}
		f[i][j][k] = ans
		return ans
	}
	return dfs(startRow, startColumn, maxMove)
}
