func rob(nums []int, colors []int) int64 {
	n := len(nums)
	var f int64 = 0
	var g int64 = int64(nums[0])

	for i := 1; i < n; i++ {
		if colors[i-1] == colors[i] {
			f, g = max(f, g), f+int64(nums[i])
		} else {
			f, g = max(f, g), max(f, g)+int64(nums[i])
		}
	}

	return max(f, g)
}
