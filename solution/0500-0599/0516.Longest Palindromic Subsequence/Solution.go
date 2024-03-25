func longestPalindromeSubseq(s string) int {
	n := len(s)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		f[i][i] = 1
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				f[i][j] = f[i+1][j-1] + 2
			} else {
				f[i][j] = max(f[i+1][j], f[i][j-1])
			}
		}
	}
	return f[0][n-1]
}