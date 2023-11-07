func maxAliveYear(birth []int, death []int) (ans int) {
	base := 1900
	d := [102]int{}
	for i, a := range birth {
		a -= base
		b := death[i] - base
		d[a]++
		d[b+1]--
	}
	mx, s := 0, 0
	for i, x := range d {
		s += x
		if mx < s {
			mx = s
			ans = base + i
		}
	}
	return
}