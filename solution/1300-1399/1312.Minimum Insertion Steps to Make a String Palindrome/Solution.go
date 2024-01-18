func minInsertions(s string) int {
	n := len(s)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= j {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		ans := 1 << 30
		if s[i] == s[j] {
			ans = dfs(i+1, j-1)
		} else {
			ans = min(dfs(i+1, j), dfs(i, j-1)) + 1
		}
		f[i][j] = ans
		return ans
	}
	return dfs(0, n-1)
}