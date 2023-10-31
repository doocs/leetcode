func maxLength(ribbons []int, k int) int {
	left, right := 0, 0
	for _, x := range ribbons {
		right = max(right, x)
	}
	for left < right {
		mid := (left + right + 1) >> 1
		cnt := 0
		for _, x := range ribbons {
			cnt += x / mid
		}
		if cnt >= k {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}