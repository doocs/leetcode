func numTimesAllBlue(flips []int) int {
	ans, mx := 0, 0
	for i := 1; i <= len(flips); i++ {
		mx = max(mx, flips[i-1])
		if mx == i {
			ans++
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}