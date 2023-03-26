func countSubstrings(s string, t string) (ans int) {
	m, n := len(s), len(t)
	f := make([][]int, m+1)
	g := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
		g[i] = make([]int, n+1)
	}
	for i, a := range s {
		for j, b := range t {
			if a == b {
				f[i+1][j+1] = f[i][j] + 1
			}
		}
	}
	for i := m - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if s[i] == t[j] {
				g[i][j] = g[i+1][j+1] + 1
			} else {
				ans += (f[i][j] + 1) * (g[i+1][j+1] + 1)
			}
		}
	}
	return
}