func buttonWithLongestTime(events [][]int) int {
	ans, t := events[0][0], events[0][1]
	for k, e := range events[1:] {
		i, t2, t1 := e[0], e[1], events[k][1]
		d := t2 - t1
		if d > t || (d == t && i < ans) {
			ans, t = i, d
		}
	}
	return ans
}
