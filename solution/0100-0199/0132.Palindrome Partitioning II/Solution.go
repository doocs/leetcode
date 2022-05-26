func minCut(s string) int {
	n := len(s)
	dp1 := make([][]bool, n)
	for i := 0; i < n; i++ {
		dp1[i] = make([]bool, n)
	}
	for i := n - 1; i >= 0; i-- {
		for j := i; j < n; j++ {
			dp1[i][j] = s[i] == s[j] && (j-i < 3 || dp1[i+1][j-1])
		}
	}
	dp2 := make([]int, n)
	for i := 0; i < n; i++ {
		if !dp1[0][i] {
			dp2[i] = i
			for j := 1; j <= i; j++ {
				if dp1[j][i] {
					dp2[i] = min(dp2[i], dp2[j-1]+1)
				}
			}
		}
	}
	return dp2[n-1]
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
