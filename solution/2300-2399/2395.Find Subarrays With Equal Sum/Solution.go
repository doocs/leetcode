func findSubarrays(nums []int) bool {
	vis := map[int]bool{}
	for i, b := range nums[1:] {
		x := nums[i] + b
		if vis[x] {
			return true
		}
		vis[x] = true
	}
	return false
}