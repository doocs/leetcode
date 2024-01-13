func numOfWays(n int) (ans int) {
	f1 := func(x int) bool {
		last := -1
		for i := 0; i < 3; i++ {
			if x%3 == last {
				return false
			}
			last = x % 3
			x /= 3
		}
		return true
	}
	f2 := func(x, y int) bool {
		for i := 0; i < 3; i++ {
			if x%3 == y%3 {
				return false
			}
			x /= 3
			y /= 3
		}
		return true
	}
	m := 27
	valid := map[int]bool{}
	f := make([]int, m)
	for i := 0; i < m; i++ {
		if f1(i) {
			valid[i] = true
			f[i] = 1
		}
	}
	d := map[int][]int{}
	for i := range valid {
		for j := range valid {
			if f2(i, j) {
				d[i] = append(d[i], j)
			}
		}
	}
	const mod int = 1e9 + 7
	for k := 1; k < n; k++ {
		g := make([]int, m)
		for i := range valid {
			for _, j := range d[i] {
				g[i] = (g[i] + f[j]) % mod
			}
		}
		f = g
	}
	for _, x := range f {
		ans = (ans + x) % mod
	}
	return
}