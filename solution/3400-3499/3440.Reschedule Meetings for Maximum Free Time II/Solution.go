func maxFreeTime(eventTime int, startTime []int, endTime []int) int {
	n := len(startTime)
	pre := make([]int, n)
	suf := make([]int, n)

	pre[0] = startTime[0]
	suf[n-1] = eventTime - endTime[n-1]

	for i := 1; i < n; i++ {
		pre[i] = max(pre[i-1], startTime[i]-endTime[i-1])
	}

	for i := n - 2; i >= 0; i-- {
		suf[i] = max(suf[i+1], startTime[i+1]-endTime[i])
	}

	ans := 0
	for i := 0; i < n; i++ {
		l := 0
		if i > 0 {
			l = endTime[i-1]
		}
		r := eventTime
		if i < n-1 {
			r = startTime[i+1]
		}
		w := endTime[i] - startTime[i]
		ans = max(ans, r-l-w)

		if i > 0 && pre[i-1] >= w {
			ans = max(ans, r-l)
		} else if i+1 < n && suf[i+1] >= w {
			ans = max(ans, r-l)
		}
	}

	return ans
}
