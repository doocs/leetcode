func removeCoveredIntervals(intervals [][]int) (ans int) {
	sort.Slice(intervals, func(i, j int) bool {
		if intervals[i][0] == intervals[j][0] {
			return intervals[i][1] > intervals[j][1]
		}
		return intervals[i][0] < intervals[j][0]
	})
	pre := math.MinInt32
	for _, e := range intervals {
		cur := e[1]
		if cur > pre {
			ans++
			pre = cur
		}
	}
	return
}
