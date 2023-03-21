func garbageCollection(garbage []string, travel []int) (ans int) {
	last := [26]int{}
	for i, s := range garbage {
		ans += len(s)
		for _, c := range s {
			last[c-'A'] = i
		}
	}
	s := make([]int, len(travel)+1)
	for i, x := range travel {
		s[i+1] = s[i] + x
	}
	for _, i := range last {
		ans += s[i]
	}
	return
}