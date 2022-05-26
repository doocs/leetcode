func hIndex(citations []int) int {
	n := len(citations)
	left, right := 0, n
	for left+1 < right {
		mid := int(uint(left+right) >> 1)
		if check(citations, mid) {
			left = mid
		} else {
			right = mid
		}
	}
	if check(citations, right) {
		return right
	}
	return left
}

func check(citations []int, mid int) bool {
	cnt := 0
	for _, citation := range citations {
		if citation >= mid {
			cnt++
		}
	}
	return cnt >= mid
}
