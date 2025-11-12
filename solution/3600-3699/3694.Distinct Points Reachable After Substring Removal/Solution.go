func distinctPoints(s string, k int) int {
	n := len(s)
	f := make([]int, n+1)
	g := make([]int, n+1)
	x, y := 0, 0
	for i := 1; i <= n; i++ {
		c := s[i-1]
		if c == 'U' {
			y++
		} else if c == 'D' {
			y--
		} else if c == 'L' {
			x--
		} else {
			x++
		}
		f[i] = x
		g[i] = y
	}
	st := make(map[int64]struct{})
	for i := k; i <= n; i++ {
		a := f[n] - (f[i] - f[i-k])
		b := g[n] - (g[i] - g[i-k])
		key := int64(a)*int64(n) + int64(b)
		st[key] = struct{}{}
	}
	return len(st)
}
