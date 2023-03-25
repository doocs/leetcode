func minTime(time []int, m int) int {
	right := 0
	for _, x := range time {
		right += x
	}
	return sort.Search(right, func(t int) bool {
		s, mx := 0, 0
		d := 1
		for _, x := range time {
			s += x
			mx = max(mx, x)
			if s-mx > t {
				s, mx = x, x
				d++
			}
		}
		return d <= m
	})
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}