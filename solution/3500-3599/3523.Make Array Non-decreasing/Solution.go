func maximumPossibleSize(nums []int) int {
	ans, mx := 0, 0
	for _, x := range nums {
		if mx <= x {
			ans++
			mx = x
		}
	}
	return ans
}