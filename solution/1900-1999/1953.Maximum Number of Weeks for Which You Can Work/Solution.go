func numberOfWeeks(milestones []int) int64 {
	mx, s := 0, 0
	for _, e := range milestones {
		mx = max(mx, e)
		s += e
	}
	rest := s - mx
	if mx > rest+1 {
		return int64(rest*2 + 1)
	}
	return int64(s)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}