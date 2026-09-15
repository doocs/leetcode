func maxPalindromes(s string, k int) int {
	n := len(s)
	g := make([][]bool, n)
	for i := range g {
		g[i] = make([]bool, n)
		for j := range g[i] {
			g[i][j] = true
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			g[i][j] = s[i] == s[j] && g[i+1][j-1]
		}
	}
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		f[i] = f[i+1]
		for j := i + k - 1; j < n; j++ {
			if g[i][j] {
				f[i] = max(f[i], 1+f[j+1])
			}
		}
	}
	return f[0]
}
