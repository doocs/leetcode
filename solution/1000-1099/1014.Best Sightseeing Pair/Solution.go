func maxScoreSightseeingPair(values []int) (ans int) {
	mx := 0
	for j, x := range values {
		ans = max(ans, mx+x-j)
		mx = max(mx, x+j)
	}
	return
}
