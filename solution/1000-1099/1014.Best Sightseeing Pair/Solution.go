func maxScoreSightseeingPair(values []int) int {
	res, mx := 0, values[0]
	for i := 1; i < len(values); i++ {
		res = max(res, values[i]-i+mx)
		mx = max(mx, values[i]+i)
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}