func minimizedMaximum(n int, quantities []int) int {
	left, right := 1, int(1e5)
	for left < right {
		mid := (left + right) >> 1
		s := 0
		for _, q := range quantities {
			s += (q + mid - 1) / mid
		}
		if s <= n {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}