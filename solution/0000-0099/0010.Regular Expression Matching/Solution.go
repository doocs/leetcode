func isMatch(s string, p string) bool {
	m, n := len(s), len(p)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		if j >= n {
			return i == m
		}
		if f[i][j] != 0 {
			return f[i][j] == 1
		}
		res := -1
		if j+1 < n && p[j+1] == '*' {
			if dfs(i, j+2) || (i < m && (s[i] == p[j] || p[j] == '.') && dfs(i+1, j)) {
				res = 1
			}
		} else if i < m && (s[i] == p[j] || p[j] == '.') && dfs(i+1, j+1) {
			res = 1
		}
		f[i][j] = res
		return res == 1
	}
	return dfs(0, 0)
}