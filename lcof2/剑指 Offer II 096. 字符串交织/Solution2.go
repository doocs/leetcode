func isInterleave(s1 string, s2 string, s3 string) bool {
	m, n := len(s1), len(s2)
	if m+n != len(s3) {
		return false
	}
	f := make([][]bool, m+1)
	for i := range f {
		f[i] = make([]bool, n+1)
	}
	f[0][0] = true
	for i := 0; i <= m; i++ {
		for j := 0; j <= n; j++ {
			k := i + j - 1
			if i > 0 && s1[i-1] == s3[k] {
				f[i][j] = f[i-1][j]
			}
			if j > 0 && s2[j-1] == s3[k] {
				f[i][j] = (f[i][j] || f[i][j-1])
			}
		}
	}
	return f[m][n]
}