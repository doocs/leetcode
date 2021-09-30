func minMeetingRooms(intervals [][]int) int {
	n := 1000010
	delta := make([]int, n)
	for _, e := range intervals {
		delta[e[0]]++
		delta[e[1]]--
	}
	res := delta[0]
	for i := 1; i < n; i++ {
		delta[i] += delta[i-1]
		res = max(res, delta[i])
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}