func eraseOverlapIntervals(intervals [][]int) int {
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][1] < intervals[j][1]
	})
	ans := len(intervals)
	pre := math.MinInt32
	for _, e := range intervals {
		l, r := e[0], e[1]
		if pre <= l {
			ans--
			pre = r
		}
	}
	return ans
}
