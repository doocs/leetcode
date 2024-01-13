func findRepeatNumber(nums []int) int {
	vis := map[int]bool{}
	for i := 0; ; i++ {
		if vis[nums[i]] {
			return nums[i]
		}
		vis[nums[i]] = true
	}
}