func arraySign(nums []int) int {
	ans := 1
	for _, v := range nums {
		if v == 0 {
			return 0
		}
		if v < 0 {
			ans *= -1
		}
	}
	return ans
}