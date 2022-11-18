func largestAltitude(gain []int) (ans int) {
	s := 0
	for _, v := range gain {
		s += v
		if ans < s {
			ans = s
		}
	}
	return
}