func smallestDivisor(nums []int, threshold int) int {
	left, right := 1, 1000000
	for left < right {
		mid := (left + right) >> 1
		s := 0
		for _, num := range nums {
			s += (num + mid - 1) / mid
		}
		if s <= threshold {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}