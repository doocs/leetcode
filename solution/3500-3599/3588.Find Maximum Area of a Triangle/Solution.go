func maxArea(coords [][]int) int64 {
	calc := func() int64 {
		mn, mx := int(1e9), 0
		f := make(map[int]int)
		g := make(map[int]int)
		for _, c := range coords {
			x, y := c[0], c[1]
			mn = min(mn, x)
			mx = max(mx, x)
			if _, ok := f[x]; ok {
				f[x] = min(f[x], y)
				g[x] = max(g[x], y)
			} else {
				f[x] = y
				g[x] = y
			}
		}
		var ans int64
		for x, y := range f {
			d := g[x] - y
			ans = max(ans, int64(d)*int64(max(mx-x, x-mn)))
		}
		return ans
	}

	ans := calc()
	for _, c := range coords {
		c[0], c[1] = c[1], c[0]
	}
	ans = max(ans, calc())
	if ans > 0 {
		return ans
	}
	return -1
}
