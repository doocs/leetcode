func maxIncreasingGroups(usageLimits []int) int {
	sort.Ints(usageLimits)
	s, k := 0, 0
	for _, x := range usageLimits {
		s += x
		if s > k {
			k++
			s -= k
		}
	}
	return k
}