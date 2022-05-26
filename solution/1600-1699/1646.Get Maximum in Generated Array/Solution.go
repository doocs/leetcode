func getMaximumGenerated(n int) int {
	if n == 0 {
		return 0
	}
	nums := make([]int, n+1)
	nums[1] = 1
	for i := 2; i <= n; i++ {
		if i%2 == 0 {
			nums[i] = nums[i>>1]
		} else {
			nums[i] = nums[i>>1] + nums[(i>>1)+1]
		}
	}
	var ans int
	for _, num := range nums {
		ans = max(ans, num)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}