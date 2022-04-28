func smallestRangeI(nums []int, k int) int {
	mx, mi := 0, 10000
	for _, v := range nums {
		mx = max(mx, v)
		mi = min(mi, v)
	}
	return max(0, mx-mi-k*2)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}