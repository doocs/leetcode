func mergeTriplets(triplets [][]int, target []int) bool {
	x, y, z := target[0], target[1], target[2]
	d, e, f := 0, 0, 0
	for _, t := range triplets {
		a, b, c := t[0], t[1], t[2]
		if a <= x && b <= y && c <= z {
			d = max(d, a)
			e = max(e, b)
			f = max(f, c)
		}
	}
	return d == x && e == y && f == z
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}