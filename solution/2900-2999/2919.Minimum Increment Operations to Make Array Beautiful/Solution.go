func minIncrementOperations(nums []int, k int) int64 {
	var f, g, h int
	for _, x := range nums {
		f, g, h = g, h, min(min(f, g), h)+max(k-x, 0)
	}
	return int64(min(min(f, g), h))
}