func maximalNetworkRank(n int, roads [][]int) (ans int) {
	g := make([][]int, n)
	cnt := make([]int, n)
	for i := range g {
		g[i] = make([]int, n)
	}
	for _, r := range roads {
		a, b := r[0], r[1]
		g[a][b], g[b][a] = 1, 1
		cnt[a]++
		cnt[b]++
	}
	for a := 0; a < n; a++ {
		for b := a + 1; b < n; b++ {
			ans = max(ans, cnt[a]+cnt[b]-g[a][b])
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}