func arrangeCoins(n int) int {
	left, right := 1, n
	for left < right {
		mid := (left + right + 1) >> 1
		s := (1 + mid) * mid >> 1
		if n < s {
			right = mid - 1
		} else {
			left = mid
		}
	}
	return left
}