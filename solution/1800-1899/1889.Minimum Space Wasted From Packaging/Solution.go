func minWastedSpace(packages []int, boxes [][]int) int {
	n := len(packages)
	inf := 1 << 62
	sort.Ints(packages)
	ans := inf
	for _, box := range boxes {
		sort.Ints(box)
		if packages[n-1] > box[len(box)-1] {
			continue
		}
		s, i := 0, 0
		for _, b := range box {
			j := sort.SearchInts(packages[i:], b+1) + i
			s += (j - i) * b
			i = j
		}
		ans = min(ans, s)
	}
	if ans == inf {
		return -1
	}
	s := 0
	for _, p := range packages {
		s += p
	}
	const mod = 1e9 + 7
	return (ans - s) % mod
}