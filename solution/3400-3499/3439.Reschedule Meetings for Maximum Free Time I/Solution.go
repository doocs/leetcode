func maxFreeTime(eventTime int, k int, startTime []int, endTime []int) int {
	n := len(endTime)
	nums := make([]int, n+1)
	nums[0] = startTime[0]
	for i := 1; i < n; i++ {
		nums[i] = startTime[i] - endTime[i-1]
	}
	nums[n] = eventTime - endTime[n-1]

	ans, s := 0, 0
	for i := 0; i <= n; i++ {
		s += nums[i]
		if i >= k {
			ans = max(ans, s)
			s -= nums[i-k]
		}
	}
	return ans
}
