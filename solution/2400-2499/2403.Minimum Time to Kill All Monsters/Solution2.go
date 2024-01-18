func minimumTime(power []int) int64 {
	n := len(power)
	dp := make([]int64, 1<<n)
	for i := range dp {
		dp[i] = math.MaxInt64
	}
	dp[0] = 0
	for mask := 1; mask < 1<<n; mask++ {
		cnt := bits.OnesCount(uint(mask))
		for i, v := range power {
			if ((mask >> i) & 1) == 1 {
				dp[mask] = min(dp[mask], dp[mask^(1<<i)]+int64((v+cnt-1)/cnt))
			}
		}
	}
	return dp[len(dp)-1]
}