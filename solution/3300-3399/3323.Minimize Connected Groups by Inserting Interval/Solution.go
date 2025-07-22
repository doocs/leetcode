func minConnectedGroups(intervals [][]int, k int) int {
	sort.Slice(intervals, func(i, j int) bool { return intervals[i][0] < intervals[j][0] })
	merged := [][]int{}
	for _, interval := range intervals {
		s, e := interval[0], interval[1]
		if len(merged) == 0 || merged[len(merged)-1][1] < s {
			merged = append(merged, interval)
		} else {
			merged[len(merged)-1][1] = max(merged[len(merged)-1][1], e)
		}
	}
	ans := len(merged)
	for i, interval := range merged {
		j := sort.Search(len(merged), func(j int) bool { return merged[j][0] >= interval[1]+k+1 })
		ans = min(ans, len(merged)-(j-i-1))
	}
	return ans
}
