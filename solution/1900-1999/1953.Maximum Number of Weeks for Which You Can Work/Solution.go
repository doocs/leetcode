func numberOfWeeks(milestones []int) int64 {
	mx := slices.Max(milestones)
	s := 0
	for _, x := range milestones {
		s += x
	}
	rest := s - mx
	if mx > rest+1 {
		return int64(rest*2 + 1)
	}
	return int64(s)
}