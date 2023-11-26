func uniqueLetterString(s string) (ans int) {
	d := make([][]int, 26)
	for i := range d {
		d[i] = []int{-1}
	}
	for i, c := range s {
		d[c-'A'] = append(d[c-'A'], i)
	}
	for _, v := range d {
		v = append(v, len(s))
		for i := 1; i < len(v)-1; i++ {
			ans += (v[i] - v[i-1]) * (v[i+1] - v[i])
		}
	}
	return
}