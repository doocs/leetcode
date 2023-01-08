func maximumCount(nums []int) int {
	a, b := 0, 0
	for _, v := range nums {
		if v > 0 {
			a++
		}
		if v < 0 {
			b++
		}
	}
	return max(a, b)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}