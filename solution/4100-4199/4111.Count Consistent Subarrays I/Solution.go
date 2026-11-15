func countConsistent(nums []int) int {
	n := len(nums)
	ans := 0
	for i, x := range nums {
		y := x
		for j := i; j < n; j++ {
			x &= nums[j]
			y = min(y, nums[j])
			if x == y {
				ans++
			}
		}
	}
	return ans
}
