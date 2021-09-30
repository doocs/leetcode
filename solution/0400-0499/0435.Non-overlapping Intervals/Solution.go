func eraseOverlapIntervals(intervals [][]int) int {
	if intervals == nil || len(intervals) == 0 {
		return 0
	}
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][1] < intervals[j][1]
	})
	end, cnt := intervals[0][1], 0
	for i := 1; i < len(intervals); i++ {
		if intervals[i][0] >= end {
			end = intervals[i][1]
		} else {
			cnt++
		}
	}
	return cnt
}