func findPoisonedDuration(timeSeries []int, duration int) (ans int) {
	ans = duration
	for i, x := range timeSeries[1:] {
		ans += min(duration, x-timeSeries[i])
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}