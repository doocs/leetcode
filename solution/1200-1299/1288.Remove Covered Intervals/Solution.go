func removeCoveredIntervals(intervals [][]int) int {
	sort.Slice(intervals, func(i, j int) bool {
		if intervals[i][0] == intervals[j][0] {
			return intervals[j][1] < intervals[i][1]
		}
		return intervals[i][0] < intervals[j][0]
	})
	cnt := 1
	pre := intervals[0]
	for i := 1; i < len(intervals); i++ {
		if pre[1] < intervals[i][1] {
			cnt++
			pre = intervals[i]
		}
	}
	return cnt
}