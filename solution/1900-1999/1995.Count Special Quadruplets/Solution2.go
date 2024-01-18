func countQuadruplets(nums []int) int {
	ans, n := 0, len(nums)
	counter := make([]int, 310)
	for c := n - 2; c > 1; c-- {
		counter[nums[c+1]]++
		for a := 0; a < c-1; a++ {
			for b := a + 1; b < c; b++ {
				ans += counter[nums[a]+nums[b]+nums[c]]
			}
		}
	}
	return ans
}