func maxScore(a []int, b []int) int64 {
	m, n := len(a), len(b)
	f := make([][]int64, m)
	for i := range f {
		f[i] = make([]int64, n)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int64
	dfs = func(i, j int) int64 {
		if j >= n {
			if i >= m {
				return 0
			}
			return math.MinInt64 / 2
		}
		if i >= m {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		f[i][j] = max(dfs(i, j+1), int64(a[i])*int64(b[j])+dfs(i+1, j+1))
		return f[i][j]
	}
	return dfs(0, 0)
}
