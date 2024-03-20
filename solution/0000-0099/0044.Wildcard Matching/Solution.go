func isMatch(s string, p string) bool {
	m, n := len(s), len(p)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		if i >= m {
			return j >= n || p[j] == '*' && dfs(i, j+1)
		}
		if j >= n {
			return false
		}
		if f[i][j] != 0 {
			return f[i][j] == 1
		}
		f[i][j] = 2
		ok := false
		if p[j] == '*' {
			ok = dfs(i+1, j) || dfs(i+1, j+1) || dfs(i, j+1)
		} else {
			ok = (p[j] == '?' || s[i] == p[j]) && dfs(i+1, j+1)
		}
		if ok {
			f[i][j] = 1
		}
		return ok
	}
	return dfs(0, 0)
}