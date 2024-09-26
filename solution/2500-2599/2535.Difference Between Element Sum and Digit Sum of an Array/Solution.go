func differenceOfSum(nums []int) int {
	var x, y int
	for _, v := range nums {
		x += v
		for ; v > 0; v /= 10 {
			y += v % 10
		}
	}
	return x - y
}
