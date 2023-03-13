func getMaximumGenerated(n int) (ans int) {
	if n < 2 {
		return n
	}
	nums := make([]int, n+1)
	nums[1] = 1
	for i := 2; i <= n; i++ {
		if i%2 == 0 {
			nums[i] = nums[i/2]
		} else {
			nums[i] = nums[i/2] + nums[i/2+1]
		}
		ans = max(ans, nums[i])
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}