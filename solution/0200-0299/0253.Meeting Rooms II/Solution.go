func minMeetingRooms(intervals [][]int) int {
	n := 1000010
	delta := make([]int, n)
	for _, e := range intervals {
		delta[e[0]]++
		delta[e[1]]--
	}
	for i := 1; i < n; i++ {
		delta[i] += delta[i-1]
	}
	return slices.Max(delta)
}