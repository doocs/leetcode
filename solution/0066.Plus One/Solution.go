func plusOne(digits []int) []int {
	for right := len(digits) - 1; right >= 0; right-- {
		digits[right] = (digits[right] + 1) % 10
		if digits[right] != 0 {
			return digits
		}
	}
	ret := make([]int, len(digits)+1)
	ret[0] = 1
	return ret
}
