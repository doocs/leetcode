func isScramble(s1 string, s2 string) bool {
	n := len(s1)
	dp := make([][][]bool, n+1)
	for i := range dp {
		dp[i] = make([][]bool, n)
		for j := range dp[i] {
			dp[i][j] = make([]bool, n+1)
		}
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			dp[i][j][1] = s1[i] == s2[j]
		}
	}
	for l := 2; l < n+1; l++ {
		for i1 := 0; i1 < n-l+1; i1++ {
			for i2 := 0; i2 < n-l+1; i2++ {
				for i := 1; i < l; i++ {
					if dp[i1][i2][i] && dp[i1+i][i2+i][l-i] {
						dp[i1][i2][l] = true
						break
					}
					if dp[i1][i2+l-i][i] && dp[i1+i][i2][l-i] {
						dp[i1][i2][l] = true
						break
					}
				}
			}
		}
	}
	return dp[0][0][n]
}
