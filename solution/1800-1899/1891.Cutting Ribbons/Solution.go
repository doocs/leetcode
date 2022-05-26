func maxLength(ribbons []int, k int) int {
	low, high := 0, 100000
	for low < high {
		mid := (low + high + 1) >> 1
		cnt := 0
		for _, ribbon := range ribbons {
			cnt += ribbon / mid
		}
		if cnt < k {
			high = mid - 1
		} else {
			low = mid
		}
	}
	return low
}