func minRange(a int, b int, c int) int {
	nums := []int{a, b, c}
	sort.Ints(nums)
	return min(nums[2]-nums[0], nums[1])
}
