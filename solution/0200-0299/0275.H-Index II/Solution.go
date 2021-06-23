func hIndex(citations []int) int {
	n := len(citations)
	left, right := 0, n
	for left < right {
		mid := (left + right) >> 1
		if citations[mid] >= n-mid {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return n - left
}