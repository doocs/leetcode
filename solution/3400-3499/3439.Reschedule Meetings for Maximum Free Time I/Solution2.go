func maxFreeTime(eventTime int, k int, startTime []int, endTime []int) int {
	n := len(endTime)
	f := func(i int) int {
		if i == 0 {
			return startTime[0]
		}
		if i == n {
			return eventTime - endTime[n-1]
		}
		return startTime[i] - endTime[i-1]
	}
	ans, s := 0, 0
	for i := 0; i <= n; i++ {
		s += f(i)
		if i >= k {
			ans = max(ans, s)
			s -= f(i - k)
		}
	}
	return ans
}
