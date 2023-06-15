func minimumDeleteSum(s1 string, s2 string) int {
	m, n := len(s1), len(s2)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i, c := range s1 {
		f[i+1][0] = f[i][0] + int(c)
	}
	for j, c := range s2 {
		f[0][j+1] = f[0][j] + int(c)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if s1[i-1] == s2[j-1] {
				f[i][j] = f[i-1][j-1]
			} else {
				f[i][j] = min(f[i-1][j]+int(s1[i-1]), f[i][j-1]+int(s2[j-1]))
			}
		}
	}
	return f[m][n]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}