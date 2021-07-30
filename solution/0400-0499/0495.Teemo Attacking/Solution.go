func findPoisonedDuration(timeSeries []int, duration int) int {
	n, res := len(timeSeries), duration
	for i := 0; i < n-1; i++ {
		res += min(duration, timeSeries[i+1]-timeSeries[i])
	}
	return res
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}