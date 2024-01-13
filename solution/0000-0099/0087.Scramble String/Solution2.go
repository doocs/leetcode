func isScramble(s1 string, s2 string) bool {
	n := len(s1)
	f := make([][][]bool, n)
	for i := range f {
		f[i] = make([][]bool, n)
		for j := 0; j < n; j++ {
			f[i][j] = make([]bool, n+1)
			f[i][j][1] = s1[i] == s2[j]
		}
	}
	for k := 2; k <= n; k++ {
		for i := 0; i <= n-k; i++ {
			for j := 0; j <= n-k; j++ {
				for h := 1; h < k; h++ {
					if (f[i][j][h] && f[i+h][j+h][k-h]) || (f[i+h][j][k-h] && f[i][j+k-h][h]) {
						f[i][j][k] = true
						break
					}
				}
			}
		}
	}
	return f[0][0][n]
}