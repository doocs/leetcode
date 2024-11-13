func maximumCandies(candies []int, k int64) int {
	return sort.Search(1e7, func(v int) bool {
		v++
		var cnt int64
		for _, x := range candies {
			cnt += int64(x / v)
		}
		return cnt < k
	})
}
