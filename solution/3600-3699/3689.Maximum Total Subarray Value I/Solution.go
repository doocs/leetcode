func maxTotalValue(nums []int, k int) int64 {
	return int64(k * (slices.Max(nums) - slices.Min(nums)))
}
