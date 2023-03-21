func brightestPosition(lights [][]int) (ans int) {
	d := map[int]int{}
	for _, x := range lights {
		l, r := x[0]-x[1], x[0]+x[1]
		d[l]++
		d[r+1]--
	}
	keys := make([]int, 0, len(d))
	for i := range d {
		keys = append(keys, i)
	}
	sort.Ints(keys)
	mx, s := 0, 0
	for _, i := range keys {
		s += d[i]
		if mx < s {
			mx = s
			ans = i
		}
	}
	return
}