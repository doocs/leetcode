func brightestPosition(lights [][]int) int {
	d := make(map[int]int)
	for _, e := range lights {
		l, r := e[0]-e[1], e[0]+e[1]
		d[l] += 1
		d[r+1] -= 1
	}

	var keys []int
	for k := range d {
		keys = append(keys, k)
	}
	sort.Ints(keys)

	s, mx, ans := 0, 0, 0
	for _, k := range keys {
		s += d[k]
		if s > mx {
			mx = s
			ans = k
		}
	}
	return ans
}