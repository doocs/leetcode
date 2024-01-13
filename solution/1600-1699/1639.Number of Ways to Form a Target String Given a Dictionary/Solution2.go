func numWays(words []string, target string) int {
	const mod = 1e9 + 7
	m, n := len(target), len(words[0])
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for j := range f[0] {
		f[0][j] = 1
	}
	cnt := make([][26]int, n)
	for _, w := range words {
		for j, c := range w {
			cnt[j][c-'a']++
		}
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			f[i][j] = f[i][j-1] + f[i-1][j-1]*cnt[j-1][target[i-1]-'a']
			f[i][j] %= mod
		}
	}
	return f[m][n]
}