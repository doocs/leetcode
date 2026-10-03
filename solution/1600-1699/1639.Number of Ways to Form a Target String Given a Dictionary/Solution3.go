func numWays(words []string, target string) int {
	m, n := len(target), len(words[0])
	cnt := make([][26]int, n)
	for _, w := range words {
		for j, c := range w {
			cnt[j][c-'a']++
		}
	}
	const mod = 1e9 + 7
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for j := range f[m] {
		f[m][j] = 1
	}
	for i := m - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			ans := f[i][j+1] + f[i+1][j+1]*cnt[j][target[i]-'a']
			f[i][j] = ans % mod
		}
	}
	return f[0][0]
}
