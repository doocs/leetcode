func maximumTotalCost(nums []int) int64 {
	f, g := math.MinInt64/2, 0
	for _, x := range nums {
		f, g = max(f, g)+x, f-x
	}
	return int64(max(f, g))
}