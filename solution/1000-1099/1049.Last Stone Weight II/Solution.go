func lastStoneWeightII(stones []int) int {
	sum := 0
	for _, stone := range stones {
		sum += stone
	}
	n := sum / 2
	dp := make([]bool, n+1)
	dp[0] = true
	for _, stone := range stones {
		for j := n; j >= stone; j-- {
			dp[j] = dp[j] || dp[j-stone]
		}
	}
	for j := n; ; j-- {
		if dp[j] {
			return sum - j - j
		}
	}
}
