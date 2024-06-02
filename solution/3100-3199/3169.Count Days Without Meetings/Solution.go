func countDays(days int, meetings [][]int) (ans int) {
	sort.Slice(meetings, func(i, j int) bool { return meetings[i][0] < meetings[j][0] })
	last := 0
	for _, e := range meetings {
		st, ed := e[0], e[1]
		if last < st {
			ans += st - last - 1
		}
		last = max(last, ed)
	}
	ans += days - last
	return
}