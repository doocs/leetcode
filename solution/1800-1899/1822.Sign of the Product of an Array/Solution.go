func arraySign(nums []int) int {
	res := 1
	for _, num := range nums {
		if num == 0 {
			return 0
		}
		if num < 0 {
			res *= -1
		}
	}
	return res
}