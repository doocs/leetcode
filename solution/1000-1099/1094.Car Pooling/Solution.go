func carPooling(trips [][]int, capacity int) bool {
	d := [1001]int{}
	for _, trip := range trips {
		x, f, t := trip[0], trip[1], trip[2]
		d[f] += x
		d[t] -= x
	}
	s := 0
	for _, x := range d {
		s += x
		if s > capacity {
			return false
		}
	}
	return true
}