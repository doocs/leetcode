func maximumTotalSum(maximumHeight []int) int64 {
	slices.SortFunc(maximumHeight, func(a, b int) int { return b - a })
	ans := int64(0)
	mx := 1 << 30
	for _, x := range maximumHeight {
		x = min(x, mx-1)
		if x <= 0 {
			return -1
		}
		ans += int64(x)
		mx = x
	}
	return ans
}
