func superEggDrop(k int, n int) int {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i < 1 {
			return 0
		}
		if j == 1 {
			return i
		}
		if f[i][j] != 0 {
			return f[i][j]
		}
		l, r := 1, i
		for l < r {
			mid := (l + r + 1) >> 1
			a, b := dfs(mid-1, j-1), dfs(i-mid, j)
			if a <= b {
				l = mid
			} else {
				r = mid - 1
			}
		}
		f[i][j] = max(dfs(l-1, j-1), dfs(i-l, j)) + 1
		return f[i][j]
	}
	return dfs(n, k)
}