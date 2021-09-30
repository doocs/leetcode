func findRightInterval(intervals [][]int) []int {
	n := len(intervals)
	starts := make([][]int, n)
	for i := 0; i < n; i++ {
		starts[i] = make([]int, 2)
		starts[i][0] = intervals[i][0]
		starts[i][1] = i
	}
	sort.Slice(starts, func(i, j int) bool {
		return starts[i][0] < starts[j][0]
	})
	var res []int
	for _, interval := range intervals {
		left, right, end := 0, n-1, interval[1]
		for left < right {
			mid := (left + right) >> 1
			if starts[mid][0] >= end {
				right = mid
			} else {
				left = mid + 1
			}
		}
		val := -1
		if starts[left][0] >= end {
			val = starts[left][1]
		}
		res = append(res, val)
	}
	return res
}