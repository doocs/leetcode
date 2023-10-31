func findValueOfPartition(nums []int) int {
	sort.Ints(nums)
	ans := 1 << 30
	for i, x := range nums[1:] {
		ans = min(ans, x-nums[i])
	}
	return ans
}