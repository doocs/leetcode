func cuttingRope(n int) int {
	if n < 4 {
		return n - 1
	}
	ans := 1
	for n > 4 {
		ans *= 3
		n -= 3
	}
	ans *= n
	return ans
}