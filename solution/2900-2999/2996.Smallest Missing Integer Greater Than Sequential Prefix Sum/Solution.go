func missingInteger(nums []int) int {
	s, j := nums[0], 1
	for j < len(nums) && nums[j] == nums[j-1]+1 {
		s, j = s+nums[j], j+1
	}
	vis := [51]bool{}
	for _, x := range nums {
		vis[x] = true
	}
	for x := s; ; x++ {
		if x >= len(vis) || !vis[x] {
			return x
		}
	}
}