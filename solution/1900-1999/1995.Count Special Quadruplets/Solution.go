func countQuadruplets(nums []int) int {
	ans, n := 0, len(nums)
	for a := 0; a < n-3; a++ {
		for b := a + 1; b < n-2; b++ {
			for c := b + 1; c < n-1; c++ {
				for d := c + 1; d < n; d++ {
					if nums[a]+nums[b]+nums[c] == nums[d] {
						ans++
					}
				}
			}
		}
	}
	return ans
}