func maximumCost(n int, highways [][]int, k int) int {
	if k >= n {
		return -1
	}
	g := make([][][2]int, n)
	for _, h := range highways {
		a, b, cost := h[0], h[1], h[2]
		g[a] = append(g[a], [2]int{b, cost})
		g[b] = append(g[b], [2]int{a, cost})
	}
	f := make([][]int, 1<<n)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -(1 << 30)
		}
	}
	for i := 0; i < n; i++ {
		f[1<<i][i] = 0
	}
	ans := -1
	for i := 0; i < 1<<n; i++ {
		for j := 0; j < n; j++ {
			if i>>j&1 == 1 {
				for _, e := range g[j] {
					h, cost := e[0], e[1]
					if i>>h&1 == 1 {
						f[i][j] = max(f[i][j], f[i^(1<<j)][h]+cost)
					}
				}
			}
			if bits.OnesCount(uint(i)) == k+1 {
				ans = max(ans, f[i][j])
			}
		}
	}
	return ans
}