func mySqrt(x int) int {
	if x == 0 {
		return 0
	}
	left, right := 1, x
	for left < right {
		mid := left + (right-left+1)>>1
		if x/mid >= mid {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}