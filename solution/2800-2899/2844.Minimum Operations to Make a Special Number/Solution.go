func minimumOperations(num string) int {
	n := len(num)
	f := make([][25]int, n)
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, k int) int
	dfs = func(i, k int) int {
		if i == n {
			if k == 0 {
				return 0
			}
			return n
		}
		if f[i][k] != -1 {
			return f[i][k]
		}
		f[i][k] = dfs(i+1, k) + 1
		f[i][k] = min(f[i][k], dfs(i+1, (k*10+int(num[i]-'0'))%25))
		return f[i][k]
	}
	return dfs(0, 0)
}