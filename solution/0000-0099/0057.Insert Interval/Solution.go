func insert(intervals [][]int, newInterval []int) [][]int {
	merge := func(intervals [][]int) (ans [][]int) {
		sort.Slice(intervals, func(i, j int) bool { return intervals[i][0] < intervals[j][0] })
		ans = append(ans, intervals[0])
		for _, e := range intervals[1:] {
			if ans[len(ans)-1][1] < e[0] {
				ans = append(ans, e)
			} else {
				ans[len(ans)-1][1] = max(ans[len(ans)-1][1], e[1])
			}
		}
		return
	}
	intervals = append(intervals, newInterval)
	return merge(intervals)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}