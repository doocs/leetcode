func validDigit(n int, x int) bool {
	hasX := false
	for n > 9 {
		hasX = hasX || (n%10 == x)
		n /= 10
	}
	return hasX && (n != x)
}
