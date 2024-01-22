func minimumCost(nums []int) int {
	a, b, c := nums[0], 100, 100
	for _, x := range nums[1:] {
		if x < b {
			b, c = x, b
		} else if x < c {
			c = x
		}
	}
	return a + b + c
}