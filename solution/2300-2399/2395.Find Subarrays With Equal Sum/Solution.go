func findSubarrays(nums []int) bool {
	s := map[int]bool{}
	for i := 0; i < len(nums)-1; i++ {
		v := nums[i] + nums[i+1]
		if s[v] {
			return true
		}
		s[v] = true
	}
	return false
}