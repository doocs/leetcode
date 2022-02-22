func numberOfGoodSubsets(nums []int) int {
	counter := make([]int, 31)
	for _, x := range nums {
		counter[x]++
	}
	const mod int = 1e9 + 7
	prime := []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29}
	n := len(prime)
	dp := make([]int, 1<<n)
	dp[0] = 1
	for x := 2; x <= 30; x++ {
		if counter[x] == 0 || x%4 == 0 || x%9 == 0 || x%25 == 0 {
			continue
		}
		mask := 0
		for i, p := range prime {
			if x%p == 0 {
				mask |= (1 << i)
			}
		}
		for state := 0; state < 1<<n; state++ {
			if (mask & state) > 0 {
				continue
			}
			dp[mask|state] = (dp[mask|state] + counter[x]*dp[state]) % mod
		}
	}
	ans := 0
	for i := 1; i < 1<<n; i++ {
		ans = (ans + dp[i]) % mod
	}
	for counter[1] > 0 {
		ans = (ans << 1) % mod
		counter[1]--
	}
	return ans
}