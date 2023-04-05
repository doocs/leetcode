func transportationHub(path [][]int) int {
	ind := [1001]int{}
	outd := [1001]int{}
	s := map[int]struct{}{}
	for _, p := range path {
		a, b := p[0], p[1]
		s[a] = struct{}{}
		s[b] = struct{}{}
		outd[a]++
		ind[b]++
	}
	for c := range s {
		if ind[c] == len(s)-1 && outd[c] == 0 {
			return c
		}
	}
	return -1
}