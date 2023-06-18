func sumOfDigits(nums []int) int {
	x := 100
	for _, v := range nums {
		if v < x {
			x = v
		}
	}
	s := 0
	for ; x > 0; x /= 10 {
		s += x % 10
	}
	return s&1 ^ 1
}