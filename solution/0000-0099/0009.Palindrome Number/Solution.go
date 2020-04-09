func isPalindrome(x int) bool {
	if x < 0 {
		return false
	}
	result := 0
	y := x
	for y != 0 {
		result = result * 10 + y%10
		y /= 10
	}
	return result == x
}