func distinctSequences(n int) int {
	if n == 1 {
		return 6
	}
	dp := make([][][]int, n+1)
	for k := range dp {
		dp[k] = make([][]int, 6)
		for i := range dp[k] {
			dp[k][i] = make([]int, 6)
		}
	}
	for i := 0; i < 6; i++ {
		for j := 0; j < 6; j++ {
			if gcd(i+1, j+1) == 1 && i != j {
				dp[2][i][j] = 1
			}
		}
	}
	mod := int(1e9) + 7
	for k := 3; k <= n; k++ {
		for i := 0; i < 6; i++ {
			for j := 0; j < 6; j++ {
				if gcd(i+1, j+1) == 1 && i != j {
					for h := 0; h < 6; h++ {
						if gcd(h+1, i+1) == 1 && h != i && h != j {
							dp[k][i][j] = (dp[k][i][j] + dp[k-1][h][i]) % mod
						}
					}
				}
			}
		}
	}
	ans := 0
	for i := 0; i < 6; i++ {
		for j := 0; j < 6; j++ {
			ans = (ans + dp[n][i][j]) % mod
		}
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}