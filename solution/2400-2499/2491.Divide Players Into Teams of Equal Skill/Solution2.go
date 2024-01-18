func dividePlayers(skill []int) int64 {
	s := 0
	for _, v := range skill {
		s += v
	}
	m := len(skill) >> 1
	if s%m != 0 {
		return -1
	}
	t := s / m
	d := [1010]int{}
	ans := 0
	for _, v := range skill {
		if d[t-v] > 0 {
			ans += v * (t - v)
			d[t-v]--
			m--
		} else {
			d[v]++
		}
	}
	if m == 0 {
		return int64(ans)
	}
	return -1
}