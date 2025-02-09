func assignElements(groups []int, elements []int) (ans []int) {
	mx := slices.Max(groups)
	d := make([]int, mx+1)
	for i := range d {
		d[i] = -1
	}
	for j, x := range elements {
		if x > mx || d[x] != -1 {
			continue
		}
		for y := x; y <= mx; y += x {
			if d[y] == -1 {
				d[y] = j
			}
		}
	}
	for _, x := range groups {
		ans = append(ans, d[x])
	}
	return
}
