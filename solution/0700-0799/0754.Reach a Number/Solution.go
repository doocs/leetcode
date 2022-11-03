func reachNumber(target int) int {
	if target < 0 {
		target = -target
	}
	var s, k int
	for {
		if s >= target && (s-target)%2 == 0 {
			return k
		}
		k++
		s += k
	}
}