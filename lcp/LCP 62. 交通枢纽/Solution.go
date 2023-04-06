func transportationHub(path [][]int) int {
	ind := [1001]int{}
	outd := [1001]int{}
	s := map[int]struct{}{}
	vis := map[int]bool{}
	for _, p := range path {
		a, b := p[0], p[1]
		if vis[a*1000+b] {
			continue
		}
		vis[a*1000+b] = true
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