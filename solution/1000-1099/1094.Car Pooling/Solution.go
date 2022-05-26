func carPooling(trips [][]int, capacity int) bool {
	delta := make([]int, 1010)
	for _, trip := range trips {
		num, start, end := trip[0], trip[1], trip[2]
		delta[start] += num
		delta[end] -= num
	}
	cur := 0
	for _, num := range delta {
		cur += num
		if cur > capacity {
			return false
		}
	}
	return true
}