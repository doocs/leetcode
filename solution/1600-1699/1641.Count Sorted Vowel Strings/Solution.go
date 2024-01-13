func countVowelStrings(n int) int {
	f := make([][5]int, n)
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= n {
			return 1
		}
		if f[i][j] != 0 {
			return f[i][j]
		}
		ans := 0
		for k := j; k < 5; k++ {
			ans += dfs(i+1, k)
		}
		f[i][j] = ans
		return ans
	}
	return dfs(0, 0)
}