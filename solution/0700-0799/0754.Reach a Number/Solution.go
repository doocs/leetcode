func reachNumber(target int) int {
	s, k := 0, 0
	if target < 0 {
		target = -target
	}
	for {
		if s >= target && (s-target)%2 == 0 {
			break
		}
		k++
		s += k
	}
	return k
}