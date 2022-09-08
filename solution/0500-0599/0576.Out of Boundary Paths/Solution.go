func findPaths(m int, n int, maxMove int, startRow int, startColumn int) int {
	f := make([][][]int, m+1)
	for i := range f {
		f[i] = make([][]int, n+1)
		for j := range f[i] {
			f[i][j] = make([]int, maxMove+1)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	var mod int = 1e9 + 7
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i < 0 || i >= m || j < 0 || j >= n {
			return 1
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		if k == 0 {
			return 0
		}
		res := 0
		for t := 0; t < 4; t++ {
			x, y := i+dirs[t], j+dirs[t+1]
			res += dfs(x, y, k-1)
			res %= mod
		}
		f[i][j][k] = res
		return res
	}
	return dfs(startRow, startColumn, maxMove)
}