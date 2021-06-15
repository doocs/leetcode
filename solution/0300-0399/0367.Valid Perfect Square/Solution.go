func isPerfectSquare(num int) bool {
	left, right := 1, num
	for left < right {
		mid := left + (right-left)>>1
		if num/mid <= mid {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left*left == num
}