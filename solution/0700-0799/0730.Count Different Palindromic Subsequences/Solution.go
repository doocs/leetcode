func countPalindromicSubsequences(s string) int {
	mod := int(1e9) + 7
	n := len(s)
	dp := make([][][]int, n)
	for i := range dp {
		dp[i] = make([][]int, n)
		for j := range dp[i] {
			dp[i][j] = make([]int, 4)
		}
	}
	for i, c := range s {
		dp[i][i][c-'a'] = 1
	}
	for l := 2; l <= n; l++ {
		for i := 0; i+l <= n; i++ {
			j := i + l - 1
			for _, c := range [4]byte{'a', 'b', 'c', 'd'} {
				k := int(c - 'a')
				if s[i] == c && s[j] == c {
					dp[i][j][k] = 2 + (dp[i+1][j-1][0]+dp[i+1][j-1][1]+dp[i+1][j-1][2]+dp[i+1][j-1][3])%mod
				} else if s[i] == c {
					dp[i][j][k] = dp[i][j-1][k]
				} else if s[j] == c {
					dp[i][j][k] = dp[i+1][j][k]
				} else {
					dp[i][j][k] = dp[i+1][j-1][k]
				}
			}
		}
	}
	ans := 0
	for _, v := range dp[0][n-1] {
		ans += v
	}
	return ans % mod
}