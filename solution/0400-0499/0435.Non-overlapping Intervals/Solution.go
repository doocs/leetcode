func eraseOverlapIntervals(intervals [][]int) int {
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][1] < intervals[j][1]
	})
	t, ans := intervals[0][1], 0
	for i := 1; i < len(intervals); i++ {
		if intervals[i][0] >= t {
			t = intervals[i][1]
		} else {
			ans++
		}
	}
	return ans
}