func minMeetingRooms(intervals [][]int) (ans int) {
	d := make(map[int]int)
	for _, e := range intervals {
		d[e[0]]++
		d[e[1]]--
	}

	keys := make([]int, 0, len(d))
	for k := range d {
		keys = append(keys, k)
	}
	sort.Ints(keys)

	s := 0
	for _, k := range keys {
		s += d[k]
		ans = max(ans, s)
	}
	return
}
