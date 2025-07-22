func longestPalindrome(s, t string) int {
	m, n := len(s), len(t)
	t = reverse(t)

	g1, g2 := calc(s), calc(t)
	ans := max(slices.Max(g1), slices.Max(g2))

	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}

	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if s[i-1] == t[j-1] {
				f[i][j] = f[i-1][j-1] + 1
				a, b := 0, 0
				if i < m {
					a = g1[i]
				}
				if j < n {
					b = g2[j]
				}
				ans = max(ans, f[i][j]*2+a)
				ans = max(ans, f[i][j]*2+b)
			}
		}
	}
	return ans
}

func calc(s string) []int {
	n, g := len(s), make([]int, len(s))
	for i := 0; i < n; i++ {
		expand(s, g, i, i)
		expand(s, g, i, i+1)
	}
	return g
}

func expand(s string, g []int, l, r int) {
	for l >= 0 && r < len(s) && s[l] == s[r] {
		g[l] = max(g[l], r-l+1)
		l, r = l-1, r+1
	}
}

func reverse(s string) string {
	r := []rune(s)
	slices.Reverse(r)
	return string(r)
}