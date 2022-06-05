func maximumCandies(candies []int, k int64) int {
	left, right := 0, int(1e7)
	for left < right {
		mid := (left + right + 1) >> 1
		var cnt int64
		for _, v := range candies {
			cnt += int64(v / mid)
		}
		if cnt >= k {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}