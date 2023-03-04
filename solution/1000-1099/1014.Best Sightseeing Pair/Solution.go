func maxScoreSightseeingPair(values []int) (ans int) {
	for j, mx := 1, values[0]; j < len(values); j++ {
		ans = max(ans, values[j]-j+mx)
		mx = max(mx, values[j]+j)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}