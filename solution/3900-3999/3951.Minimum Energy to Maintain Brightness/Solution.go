func minEnergy(n int, brightness int, intervals [][]int) int64 {
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] < intervals[j][0]
	})
	merged := [][]int{intervals[0]}
	for _, x := range intervals[1:] {
		if merged[len(merged)-1][1] < x[0] {
			merged = append(merged, x)
		} else {
			if x[1] > merged[len(merged)-1][1] {
				merged[len(merged)-1][1] = x[1]
			}
		}
	}
	ans := 0
	for _, interval := range merged {
		start := interval[0]
		end := interval[1]
		m := end - start + 1
		ans += (brightness + 2) / 3 * m
	}
	return int64(ans)
}