func minWindow(s1 string, s2 string) string {
	m, n := len(s1), len(s2)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if s1[i-1] == s2[j-1] {
				if j == 1 {
					f[i][j] = i
				} else {
					f[i][j] = f[i-1][j-1]
				}
			} else {
				f[i][j] = f[i-1][j]
			}
		}
	}
	p, k := 0, m+1
	for i := 1; i <= m; i++ {
		if s1[i-1] == s2[n-1] && f[i][n] > 0 {
			j := f[i][n] - 1
			if i-j < k {
				k = i - j
				p = j
			}
		}
	}
	if k > m {
		return ""
	}
	return s1[p : p+k]
}