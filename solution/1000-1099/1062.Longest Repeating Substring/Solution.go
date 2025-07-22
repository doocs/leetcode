func longestRepeatingSubstring(s string) (ans int) {
	n := len(s)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
	}
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			if s[i] == s[j] {
				if j > 0 {
					f[i][j] = f[i-1][j-1]
				}
				f[i][j]++
				ans = max(ans, f[i][j])
			}
		}
	}
	return
}
